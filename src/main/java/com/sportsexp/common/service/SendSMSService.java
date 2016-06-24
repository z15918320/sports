package com.sportsexp.common.service;

import java.util.Map;


/**
 * * <p>Title:SendSMSService </p>
 * <p>Description:短信发送 </p>
 * <p>Company: sportsexp </p> 
 * @author zhijiang.zhang
 * @date 2016年6月14日 下午5:54:00
 */
public interface SendSMSService {
	/**
	 * 发送短信
	 * @param number
	 * @param context
	 * @return
	 */
	public String sendSMS(Map<String,String> msgInfo) throws Exception;
}
