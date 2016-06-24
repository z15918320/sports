package com.sportsexp.smssendlog.pojo;

import java.util.Date;

public class SmsSendDetailParams {
	/**
	 * 主键ID
	 */
	private String sms_send_detail_id;
	
	/**
	 * 接收人手机号码
	 */
	private String send_mobile;
	
	/**
	 * 发送状态
	 */
	private String send_status;
	
	/**
	 * 主表ID
	 */
	private String sms_send_id;
	
	/**
	 * 发送时间
	 */
	private Date send_time;
	
	
	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public String getSms_send_id() {
		return sms_send_id;
	}

	public void setSms_send_id(String sms_send_id) {
		this.sms_send_id = sms_send_id;
	}

	public String getSms_send_detail_id() {
		return sms_send_detail_id;
	}

	public void setSms_send_detail_id(String sms_send_detail_id) {
		this.sms_send_detail_id = sms_send_detail_id;
	}

	public String getSend_mobile() {
		return send_mobile;
	}

	public void setSend_mobile(String send_mobile) {
		this.send_mobile = send_mobile;
	}

	public String getSend_status() {
		return send_status;
	}

	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}
	
	
}
