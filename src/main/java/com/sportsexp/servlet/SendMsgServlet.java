package com.sportsexp.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sportsexp.activities.pojo.LoginParams;
import com.sportsexp.activities.service.ActivitiesService;
import com.sportsexp.activities.service.impl.ActivitiesServiceImpl;
import com.sportsexp.common.service.SendSMSService;
import com.sportsexp.common.service.impl.PostMsgServiceImpl;
import com.sportsexp.user.vo.UserInfo;

/**
 * 
 * @author zhijiang.zhang
 * @version 2016-06-15 09:47:05
 * @intro 短信发送接口
 */
public class SendMsgServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

//	@SuppressWarnings("rawtypes")
//	public void doPost(HttpServletRequest request, HttpServletResponse response) {
//		 try {
//			doGet(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		synchronized (this){
			try {
				ActivitiesService activitiesService = (ActivitiesServiceImpl) SpringContextUtil.getBean("ActivitiesService", ActivitiesServiceImpl.class);
				request.setCharacterEncoding("UTF-8");
				String userName = request.getParameter("userName");
				String pwd = request.getParameter("pwd");
				if(StringUtils.isNotEmpty(userName)&&StringUtils.isNotEmpty(pwd)){
					LoginParams loginInfo = new LoginParams();
					loginInfo.setPassword(pwd.trim());
					loginInfo.setUsername(userName.trim());
					UserInfo userinfo = activitiesService.loginActivities(loginInfo);
					SendSMSService sendMsgService=null;
					if(null!=userinfo){
						sendMsgService = new PostMsgServiceImpl();
						Map<String,String> msgInfo = new HashMap();
						msgInfo.put(request.getParameter("pnb"), request.getParameter("context"));
						sendMsgService.sendSMS(msgInfo);
					}
				}
				
				response.setContentType("multipart/form-data;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
			} catch (Exception e) {						
				e.printStackTrace();
			} finally {
				
			}
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	

}