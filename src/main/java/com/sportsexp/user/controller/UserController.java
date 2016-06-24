package com.sportsexp.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sportsexp.common.util.DateUtil;
import com.sportsexp.common.util.MD5Utils;
import com.sportsexp.user.pojo.UserParams;
import com.sportsexp.user.service.UserService;
import com.sportsexp.user.vo.UserInfo;

/**
 * 用户管理controller
 * @author zhijiang.zhang
 * 2016-4-21 11:09:11
 */
@Controller 
@RequestMapping("/UserActivities")
public class UserController {

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
	
	String user_name;
	String login_name;
	String nickname;
	String identity_key="";
	String company_name;
	String starTime;
	String endTime;
	String entityStatus="";
	String isFirst;//首页跳转
	Integer pageCountt;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 改变用户状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/userUnactive.do")
	public String toUnActiveActivity(HttpServletRequest request,String allIDCheck,RedirectAttributes attr) {
	  String [] userIds = allIDCheck.split(",");
	  boolean isdelete = true;
	  if(null!=userIds&&userIds.length>0){
		  for (String uid : userIds) {
			  isdelete = this.userService.deleteUserActivities(uid);
			  if(!isdelete){
					break;
				}
		}
	  }
	  if(isdelete){
		  attr.addAttribute("message", "操作用户成功");
		}else{
			attr.addAttribute("message", "操作用户失败");
		}
	 return "redirect:/UserActivities/toUserList.do";
	}
	
	/**
	 * 用户编辑提交逻辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUserSubmit.do")  
	@ResponseBody
	public Map toUserSubmit(HttpServletRequest request,@RequestBody UserParams userPar){
		Map<String,String> map = new HashMap<String,String>();
		try{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			UserInfo userinfo = (UserInfo)session.getAttribute("user");
			boolean isadd;
			if(null!=userinfo){
				String password = userPar.getPassword();
				//修改
				if(StringUtils.isNotEmpty(userPar.getUser_info_id())){
					UserInfo tmpUser = this.userService.getUserInfoFroIdActivities(userPar.getUser_info_id());
					//如果有修改密码否则不修改密码
					if(!password.equals(tmpUser.getPASSWORD())){
						userPar.setPassword(MD5Utils.GetMD5Code(password));
					}
					isadd = this.userService.editUserActivities(userPar);
				}else{//新增
					userPar.setPassword(MD5Utils.GetMD5Code(password));
					isadd = this.userService.addUserActivities(userPar);
				}
				if(isadd){
					map.put("message", "操作用户成功");
				}else{
					map.put("message", "操作用户失败");
				}
				
			}
		}catch(Exception e){
			map.put("message", "操作用户失败");
		}
		return map;
	}

	
	
	/**
	 * 用户编辑页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUserEdit.do")  
	public String toUserEdit(HttpServletRequest request){
		String returnStr="/jsp/user/userEdit";
		HttpSession session = request.getSession();
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			//获得用户ID
			String userid = request.getParameter("userid");
			UserInfo userInfo = new UserInfo();
			if(StringUtils.isNotEmpty(userid)){
				userInfo = this.userService.getUserInfoFroIdActivities(userid);
			}
			if(null!=userInfo.getBIRTHDAY()){
				userInfo.setBirthdayStr(DateUtil.formatDateYMDToStr(userInfo.getBIRTHDAY()));
			}
			request.setAttribute("userInfo", userInfo);
		}else{
			returnStr = "login";
		}
		return returnStr;
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
		
		
		return "redirect:/UserActivities/toUserList.do";
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
		return "redirect:/UserActivities/toUserList.do";
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
		return "redirect:/UserActivities/toUserList.do";
	}
	
	
	@RequestMapping("/toUserList.do")  
	public String toUserList(HttpServletRequest request){
		String returnStr="/jsp/user/userList";
		HttpSession session = request.getSession();
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			if(StringUtils.isNotEmpty(request.getParameter("login_name"))){
				login_name = request.getParameter("login_name");
			}else{
				login_name = null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("user_name"))){
				user_name = request.getParameter("user_name");
			}else{
				user_name = null;
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
			if(StringUtils.isNotEmpty(request.getParameter("company_name"))){
				company_name = request.getParameter("company_name");
			}else{
				company_name = null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("nickname"))){
				nickname = request.getParameter("nickname");
			}else{
				nickname = null;
			}
			if(StringUtils.isNotEmpty(request.getParameter("identity_key"))){
				identity_key = request.getParameter("identity_key").trim();
			}
			UserParams userPar = new UserParams();
			if(StringUtils.isNotEmpty(starTime)){
				userPar.setStarTime(starTime);
			}
			if(StringUtils.isNotEmpty(endTime)){
				userPar.setEndTime(endTime);
			}
			if(StringUtils.isNotEmpty(entityStatus.trim())){
				userPar.setStatus(entityStatus);
			}else{
				userPar.setStatus(null);
			}
			userPar.setUser_name(user_name);
			userPar.setLogin_code(login_name);
			userPar.setNick_name(nickname);
			userPar.setCompany_name(company_name);
			userPar.setUser_type(identity_key);
			userPar.setStarNumber(starNumber);
			userPar.setEndNumber(endNumber);
			List<UserInfo> userInfoList = this.userService.getUserInfoFroParActivities(userPar);
			custCount = this.userService.getCountUserInfoActivities(userPar);
			Double c = Math.ceil(custCount/number.doubleValue());
			pageCount = c.intValue();
			request.setAttribute("infoList", userInfoList);
			request.setAttribute("selectUserName", user_name);
			request.setAttribute("selectLoginName", login_name);
			request.setAttribute("selectNickName", nickname);
			request.setAttribute("selectCompanyName", company_name);
			request.setAttribute("starTime", starTime);
			request.setAttribute("endTime", endTime);
			request.setAttribute("entityStatus", userPar.getStatus());
			request.setAttribute("identity_key", userPar.getUser_type());
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
	
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toExpExcel.do")  
	public String toExpExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String path = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath()
                + "download/";//这个download目录为啥建立在classes下的
		String fileName="userInfo"+System.currentTimeMillis()+".xls";
		HttpSession session = request.getSession();
		response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
		UserInfo userinfo = (UserInfo)session.getAttribute("user");
		if(null!=userinfo){
			UserParams userPar = new UserParams();
			if(StringUtils.isNotEmpty(starTime)){
				userPar.setStarTime(starTime);
			}
			if(StringUtils.isNotEmpty(endTime)){
				userPar.setEndTime(endTime);
			}
			if(StringUtils.isNotEmpty(entityStatus)){
				userPar.setStatus(entityStatus);
			}
			userPar.setUser_name(user_name);
			userPar.setStarNumber(null);
//			List<UserInfo> userInfoList = this.userService.getUserInfoFroParActivities(userPar);
			List<UserInfo> userInfoList = new ArrayList<UserInfo>();
			// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件  
			
			File tmpFile = new File(path);
			if (!tmpFile.exists()) {
				// 创建临时目录
				tmpFile.mkdir();
			}
			
			 File file = new File(path+fileName);   
			WritableWorkbook writeBook = Workbook.createWorkbook(file);
			 // 2、新建工作表(sheet)对象，并声明其属于第几页  
	        WritableSheet sheet = writeBook.createSheet("用户信息", 0);// 第一个参数为工作簿的名称，第二个参数为页数  
	        WritableCellFormat wc = new WritableCellFormat();   
	        // 设置居中   
	         wc.setAlignment(Alignment.CENTRE);
	         String[] title = {"ID","登录名","用户名称","性别","公司名称","地址","电话","积分","注册日期","用户级别","行业","状态"};  
	        Label titleLable;// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容 
	        for(int i=0;i<title.length;i++){   
	            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z   
	            // 在Label对象的子对象中指明单元格的位置和内容   
	        	titleLable = new Label(i,0,title[i],wc); 
	            // 将定义好的单元格添加到工作表中   
	            sheet.addCell(titleLable);   
	        }   
			int i=0;
			int j=1;
			for (UserInfo userInfo : userInfoList) {
				String[]info=new String[12];
				/*info[0]=userInfo.getUser_id()+"";
				info[1]=userInfo.getUser_name();
				info[2]=userInfo.getNickname();
				info[3]=userInfo.getSexy().equals(1)?"男":"女";
				info[4]=userInfo.getCompany_name();
				info[5]=userInfo.getAddress();
				info[6]=userInfo.getMobile();
				info[7]=userInfo.getBehavior_value()+"";
				info[8]=userInfo.getRegister_time()+"";
				info[9]=userInfo.getIdentity_value()+"";
				info[10]=userInfo.getI_name()+"";
				info[11]=userInfo.getStatus().equals(1)?"失效":"生效";*/
				Label infoLable;
				for(int k=0;k<title.length;k++){   
		            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z   
		            // 在Label对象的子对象中指明单元格的位置和内容   
					infoLable = new Label(k,j,info[k],wc); 
		            // 将定义好的单元格添加到工作表中   
		            sheet.addCell(infoLable);   
		        }  
				j++;
			}
			 // 4、打开流，开始写文件  
	        writeBook.write();  
	  
	        // 5、关闭流  
	        writeBook.close();  
	        InputStream inputStream = new FileInputStream(new File(path+fileName));
	        OutputStream os = response.getOutputStream();
	        byte[] b = new byte[2048];
	        int length;
	        while ((length = inputStream.read(b)) > 0) {
	            os.write(b, 0, length);
	        }

	         // 这里主要关闭。
	        os.close();

	        inputStream.close();
	        file.delete();
	        
		}else{
			return "login";
		}
		return null;
	}
}
