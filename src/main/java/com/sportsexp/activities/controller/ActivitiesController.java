package com.sportsexp.activities.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsexp.activities.pojo.LoginParams;
import com.sportsexp.activities.pojo.MsgReturn;
import com.sportsexp.activities.service.ActivitiesService;
import com.sportsexp.activities.vo.SelectValue;
import com.sportsexp.common.service.SendSMSService;
import com.sportsexp.common.util.MD5Utils;
import com.sportsexp.common.util.StringUtil;
import com.sportsexp.smssendlog.pojo.SmsSendDetailParams;
import com.sportsexp.smssendlog.pojo.SmsSendParams;
import com.sportsexp.smssendlog.service.SMSSendMsgLogService;
import com.sportsexp.user.pojo.UserParams;
import com.sportsexp.user.service.UserService;
import com.sportsexp.user.vo.UserInfo;
@Controller 
public class ActivitiesController {
	
	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SendSMSService sendSMSService;
	
	@Autowired
	private SMSSendMsgLogService smsLogService;

	/**
	 * 短信发送接口
	 * @param uname 用户名
	 * @param pwd 密码
	 * @param pnb 电话号码
	 * @param context 发送文本
	 * @param request
	 * @param response
	 * @return 发送结果 json格式
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/sendSMS/{uname}&{pwd}&{pnb}&{context}",produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
	public String sendSMSForInterface(@PathVariable String uname,@PathVariable String pwd, @PathVariable String pnb,@PathVariable String context,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MsgReturn ret = new MsgReturn();
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");   
        response.setContentType("text/json;charset=UTF-8");  
        response.setCharacterEncoding("UTF-8");  
		if(StringUtils.isNotEmpty(uname)&&StringUtils.isNotEmpty(pwd)){
			LoginParams loginInfo = new LoginParams();
			loginInfo.setPassword(MD5Utils.GetMD5Code(pwd.trim()));
			loginInfo.setUsername(uname.trim());
			//判断用户是否存在
			UserInfo userinfo = this.activitiesService.loginActivities(loginInfo);
			//如果存在则可以发送短信
			if(null!=userinfo){
				if (!StringUtil.isChineseChar(context))  
				 {  
					context = new String(context.getBytes("iso8859-1"), "utf-8");  
				 }
				sendSMS(pnb, context, ret, userinfo);
			}else{
				ret.setErrorMsg("您输入的用户名或密码错误!");
				ret.setTag("ERROR");
			}
		}
		 ObjectMapper mapper = new ObjectMapper();
	     String json = mapper.writeValueAsString(ret);
		return json;
	}

	/**
	 * 用户编辑提交逻辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/toSMSSendSubmit.do")  
	@ResponseBody
	public Map toSMSSendSubmit(HttpServletRequest request,@RequestBody SelectValue par){
		Map<String,String> map = new HashMap<String,String>();
		MsgReturn ret = new MsgReturn();
		HttpSession session = request.getSession();
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			try {
				sendSMS(par.getPhoneNum(),par.getSendContext(),ret,userinfo);
				map.put("message", "发送成功!");
			} catch (Exception e) {
				map.put("message", "发送失败!");
			}
		}else{
			map.put("message", "未登陆用户!");
		}
		
		return map;
	}

	private void sendSMS(String pnb, String context, MsgReturn ret,
			UserInfo userinfo) throws Exception {
		Map<String,String> msgInfo = new HashMap();
		msgInfo.put(pnb, context);
		String returnStr = sendSMSService.sendSMS(msgInfo);
		String returnTag = "SUCCESS";	//返回成功标识
		String returnMsg= "SUCCESS";	//报错内容
		int i=1;//发送次数
		String retStatus="1";
		if(!"SUCCESS".equals(returnStr)){
			//如果调用不成功则重复调用三次,三次都失败则判定发送失败
			for (i=1; i < 4; i++) {
				returnStr = sendSMSService.sendSMS(msgInfo);
				if("SUCCESS".equals(returnStr)){
					break;
				}
			}
			returnMsg = returnStr;
			if(!"SUCCESS".equals(returnStr)){
				returnTag = "ERROR";
				retStatus = "2";
			}
		}
		ret.setTag(returnTag);
		ret.setErrorMsg(returnMsg);
		SmsSendParams ssp = new SmsSendParams();
		ssp.setSms_send_content(context);
		ssp.setSms_send_time(new Date());
		ssp.setUser_info_id(userinfo.getUSER_INFO_ID());
		smsLogService.addSmsSend(ssp);
		
		SmsSendDetailParams ssdp = new SmsSendDetailParams();
		ssdp.setSend_mobile(pnb);
		ssdp.setSend_time(new Date());
		ssdp.setSms_send_id(ssp.getSms_send_id());
		ssdp.setSend_status(retStatus);
		smsLogService.addSmsSendDetail(ssdp);
	}
	
    		
	/**
	 * 登陆控制
	 * @param request
	 * @param username
	 * @param password
	 * @param attr
	 * @return
	 */
	@RequestMapping("/login.do")  
    public String login(HttpServletRequest request,String username,String password,RedirectAttributes attr){ 
		String returnStr="login";
		HttpSession session = request.getSession();
		if(!StringUtils.isEmpty(username)){
			LoginParams loginInfo = new LoginParams();
			loginInfo.setPassword(MD5Utils.GetMD5Code(password.trim()));
			loginInfo.setUsername(username.trim());
			UserInfo userinfo = this.activitiesService.loginActivities(loginInfo);
			if(null!=userinfo){
				session.setAttribute("user", userinfo);
				UserParams up = new UserParams();
				up.setUser_info_id(userinfo.getUSER_INFO_ID());
				up.setPre_login_time(userinfo.getLAST_LOGIN_TIME());
				up.setLast_login_time(new Date());
				userService.editUserActivities(up);
				returnStr = "redirect:/toIndex.do";
			}else{
				attr.addAttribute("message", "登录失败");  
				
			}
		}else{
			session.invalidate();
		}
		return returnStr;
    }
	
	/**
	 * 跳转到首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toIndex.do")  
	public String toIndex(HttpServletRequest request){
		String returnStr="/jsp/index"; 
		HttpSession session = request.getSession();
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			request.setAttribute("username", userinfo.getNICK_NAME());
		}else{
			returnStr = "login";
		}
		return returnStr;
	}
	
	
}