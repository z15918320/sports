package com.sportsexp.smssendlog.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsexp.common.service.SendSMSService;
import com.sportsexp.smssendlog.pojo.SmsSendDetailParams;
import com.sportsexp.smssendlog.pojo.SmsSendLogParams;
import com.sportsexp.smssendlog.service.SMSSendMsgLogService;
import com.sportsexp.smssendlog.vo.SmsSendLog;
import com.sportsexp.user.service.UserService;
import com.sportsexp.user.vo.UserInfo;
@Controller 
@RequestMapping("/SMSSendMsgLogActivities")
public class SMSSendMsgLogController {
	/**
	 * 一页显示条数
	 */
	private Integer number=10;
	
	/**
	 * 起始数
	 */
	private Integer starNumber=0;
	
	/**
	 * 起始数
	 */
	private Integer endNumber=10;
	
	/**
	 * 总页数  
	 */
	private Integer pageCount=1;
	
	/**
	 * 用户总数
	 */
	private Integer custCount=0;
	
	/**
	 * 当前页数
	 */
	private Integer pageNow =1;
	
	String sendNum;
	String sendText;
	String entityStatus="";
	String starTime;
	String endTime;
	Integer pageCountt;
	@Autowired
	private SendSMSService sendSMSService;
	@Autowired
	private SMSSendMsgLogService service;
	@Autowired
	private UserService userService;
	/**
	 * 短信重发
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/reSendSMS.do")
	public String reSendSMS(HttpServletRequest request,String allIDCheck,RedirectAttributes attr) throws Exception {
	  String [] logIds = allIDCheck.split(",");
	  String returnStatus = "";
	 
	  if(null!=logIds&&logIds.length>0){
		  for (String logId : logIds) {
			  returnStatus = sendSMS(logId); 
		}
		  
		 
	  }
	  if("SUCCESS".equals(returnStatus)){
		  attr.addAttribute("message", "重发成功");
		}else{
		  attr.addAttribute("message", "重发失败:"+returnStatus);
		}
	 return "redirect:/SMSSendMsgLogActivities/toSmsSendLogList.do";
	}

	private String sendSMS(String logId) throws Exception {
		String returnStatus;
		Map<String,String> msgInfo = new HashMap();
		  SmsSendLog log = service.getEntityListFroID(logId);
		  //判断用户是否存在
		  UserInfo userinfo = userService.getUserInfoFroIdActivities(log.getUSER_INFO_ID());
		  if(null!=userinfo){
			  msgInfo.put(log.getSEND_MOBILE(), log.getSMS_SEND_CONTENT());
			  returnStatus = this.sendSMSService.sendSMS(msgInfo);
		  }else{
			  returnStatus="不存在的用户"; 
		  }
		  //记录日志
		  SmsSendDetailParams ssd = new SmsSendDetailParams();
		  if("SUCCESS".equals(returnStatus)){
			  ssd.setSend_status("1"); 
		  }else{
			  ssd.setSend_status("2"); 
		  }
		  ssd.setSend_time(new Date());
		  ssd.setSms_send_detail_id(log.getSMS_SEND_DETAIL_ID());
		  service.editEntity(ssd);
		return returnStatus;
	}
	
	/**
	 * index页面查询下一页用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectNextPage.do")
	public String selectNextPage(HttpServletRequest request){
		if(StringUtils.isNotEmpty(request.getParameter("isEnd"))){
			pageNow=pageCount;
			endNumber = pageNow*number;
			starNumber=endNumber-number+1;
		}else{
			pageNow+=1;
			starNumber=endNumber+1;
			endNumber = pageNow*number;
		}
		
		
		return "redirect:/SMSSendMsgLogActivities/toSmsSendLogList.do";
	}
	
	/**
	 * index页面查询上一页用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectLastPage.do")
	public String selectLastPage(HttpServletRequest request){
		if(pageNow>1){
			if(StringUtils.isNotEmpty(request.getParameter("isFirst"))){
				pageNow=1;
				starNumber=0;
			}else{
				pageNow-=1;
				starNumber = starNumber-number;
				
			}
			endNumber = pageNow*number;
			
		}
		return "redirect:/SMSSendMsgLogActivities/toSmsSendLogList.do";
	}
	
	/**
	 * 直接跳转查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/jumpInputPage.do")
	public String jumpInputPage(HttpServletRequest request){
		if(StringUtils.isNotEmpty(request.getParameter("pageCountt"))){
			pageCountt = new Integer(request.getParameter("pageCountt"));
			pageNow=pageCountt;
			endNumber = pageNow*number;
			starNumber=endNumber-number+1;
		}
		return "redirect:/SMSSendMsgLogActivities/toSmsSendLogList.do";
	}
	
	@RequestMapping("/toSmsSendLogList.do")  
	public String toSmsSendLogList(HttpServletRequest request){
		String returnStr="/jsp/smsSendLog/smsSendLog";
		HttpSession session = request.getSession();
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			if(StringUtils.isNotEmpty(request.getParameter("sendText"))){
				sendText = request.getParameter("sendText");
			}else{
				sendText = null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("sendNum"))){
				sendNum = request.getParameter("sendNum");
			}else{
				sendNum = null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("entityStatus"))){
				entityStatus = request.getParameter("entityStatus").trim();
			}
			if(StringUtils.isNotEmpty(request.getParameter("starTime"))){
				starTime = request.getParameter("starTime");
			}else{
				starTime=null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("endTime"))){
				endTime = request.getParameter("endTime");
			}else{
				endTime = null;
			}
			SmsSendLogParams entityPar = new SmsSendLogParams();
			entityPar.setStarTime(starTime);
			entityPar.setEndTime(endTime);
			entityPar.setSend_mobile(sendNum);
			entityPar.setSms_send_content(sendText);
			if(StringUtils.isNotEmpty(entityStatus.trim())){
				entityPar.setSend_status(entityStatus);
			}else{
				entityPar.setSend_status(null);
			}
			entityPar.setStarNumber(starNumber);
			entityPar.setEndNumber(endNumber);
			List<SmsSendLog> SmsSendLogList = this.service.getEntityListFroPar(entityPar);
			custCount = this.service.getCountEntity(entityPar);
			Double c = Math.ceil(custCount/number.doubleValue());
			pageCount = c.intValue();
			request.setAttribute("infoList", SmsSendLogList);
			request.setAttribute("selectSendNum", sendNum);
			request.setAttribute("selectSendText", sendText);
			request.setAttribute("entityStatus", entityPar.getSend_status());
			request.setAttribute("starTime", starTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("number", number);
			request.setAttribute("starNumber", starNumber);
			request.setAttribute("custCount", custCount);
			request.setAttribute("pageNow", pageNow);
			
		}else{
			returnStr = "login";
		}
		return returnStr;
		
	}

	
}