package com.sportsexp.smssendlog.pojo;

import java.util.Date;

public class SmsSendParams {
	/**
	 * 主键ID
	 */
	private String sms_send_id;
	
	/**
	 * 用户ID
	 */
	private String user_info_id;
	
	/**
	 * 发送内容
	 */
	private String sms_send_content;
	
	/**
	 * 发送时间
	 */
	private Date sms_send_time;

	public String getSms_send_id() {
		return sms_send_id;
	}

	public void setSms_send_id(String sms_send_id) {
		this.sms_send_id = sms_send_id;
	}

	public String getUser_info_id() {
		return user_info_id;
	}

	public void setUser_info_id(String user_info_id) {
		this.user_info_id = user_info_id;
	}

	public String getSms_send_content() {
		return sms_send_content;
	}

	public void setSms_send_content(String sms_send_content) {
		this.sms_send_content = sms_send_content;
	}

	public Date getSms_send_time() {
		return sms_send_time;
	}

	public void setSms_send_time(Date sms_send_time) {
		this.sms_send_time = sms_send_time;
	}
}
