package com.sportsexp.common.service.impl;

import java.net.ConnectException;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Service;

import com.esms.ConfigInfo;
import com.esms.MessageData;
import com.esms.PostMsg;
import com.sportsexp.common.service.SendSMSService;
import com.sportsexp.common.util.PropertiesUtils;

/**
 * * <p>Title:PostMsgServiceImpl </p>
 * <p>Description: 玄武短信发送平台</p>
 * <p>Company: sportsexp </p> 
 * @author zhijiang.zhang
 * @date 2016年6月14日 下午5:56:27
 */
@Service
public class PostMsgServiceImpl implements SendSMSService {
	
	@Override
	public String sendSMS(Map<String,String> msgInfo) throws Exception {
		
		String status="SUCCESS";
		PropertiesConfiguration pc = PropertiesUtils.getSmsroPertiesConfiguration();
		String uName = pc.getProperty("USER_NAME")+"".trim();
		String pwd = pc.getProperty("PWD")+"".trim();
		String cmIP = pc.getProperty("CMIP")+"".trim();
		Integer cmPort = new Integer(pc.getProperty("CMPORT").toString().trim());
		String wsIP = pc.getProperty("WSIP")+"".trim();
		Integer wsPort = new Integer(pc.getProperty("WSPORT").toString().trim());
		PostMsg msg = new PostMsg(uName, pwd);
		
		//设置网关服务器，该服务器用于发送信息，不同的情况下连接服务器有不同的发送速度，
		//所以强烈推荐代码里面有能够修改服务器的选项，比如做成配置文件的格式
		msg.getCmHost().setHost(cmIP, cmPort);
		
		//设置Web Service服务器，该服务器用于接收信息，不同的情况下连接服务器有不同的发送速度，
		//所以强烈推荐代码里面有能够修改服务器的选项，比如做成配置文件的格式
		msg.getWsHost().setHost(wsIP, wsPort);
		
//		//如果是通过代理服务器上网，可以用如下的方式告诉接口代理服务器的信息和用户名密码
//		msg.getProxy().setProxy(ProxyServer.PROXY_TYPE_HTTP, "192.168.0.171", 808,
//			"one", "one");

		//显示余额
		int remainFee = msg.getRemainFee();
		if (remainFee < 0){
			status = "获取余额失败，原因为：" + esmsErrorMessage(remainFee) + "代号为：" + remainFee;
			System.out.println("获取余额失败，原因为：" + esmsErrorMessage(remainFee) + "代号为：" + remainFee);	
		}else{
			System.out.println("余额为：" + remainFee + "分");				
		}
		
		MessageData[] msgData = new MessageData[msgInfo.size()];
		int i=0;
		for (String key : msgInfo.keySet()) {
			System.out.println("调用短信接口啦~~~~~~~~~~~~~~~~~~~接收号码为:"+key+"发送内容为:"+msgInfo.get(key));
			msgData[i] = new MessageData(key, msgInfo.get(key));
			i++;
		}
		if (msg.post(msgData, null) == 0){
			System.out.println("短信发送成功，发送后的余额是" + msg.getRemainFee());							
		}else{
			status = "短信发送失败";
		}			
		
		try {
			ConfigInfo info = msg.getConfigInfo();
			if (info == null){
				status = "获取帐号信息失败，用户名密码错误";
			}else{
				System.out.println(info.toString());
			}		
			
		} catch (ConnectException e) {
			status = "操作失败，连接不上服务器";
			e.printStackTrace();		
		}
		return status;
	}
	
	private static String esmsErrorMessage(int errorCode)
	{
		switch (errorCode)
		{
			case PostMsg.E_INVALID_USER_PASSWORD:
				return "用户名密码错误";

			case PostMsg.E_FAILED_CONNECTED_TO_SERVER:
				return "连接不上服务器";				

			case PostMsg.E_FAILED_TO_POST_MESSAGE:
				return "短信发送失败，或者余额不足，或者密码错误";				

			case PostMsg.E_INVALID_MESSAGE_DATA:
				return "无效的短信号码或者内容";				

			default:
				return "未知错误";				
		}
	}
}
