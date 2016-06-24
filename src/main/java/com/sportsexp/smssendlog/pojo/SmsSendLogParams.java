package com.sportsexp.smssendlog.pojo;



public class SmsSendLogParams {
	/**
	 * 起始数
	 */
	private static Integer starNumber=0;
	
	/**
	 * 结束数
	 */
	private static Integer endNumber=0;
	/**
	 * 主键ID
	 */
	private String sms_send_id;
	
	private String user_info_id;
	/**
	 * 发送人
	 */
	private String user_name;
	
	/**
	 * 接收手机号码
	 */
	private String send_mobile;
	/**
	 * 发送内容
	 */
	private String sms_send_content;
	
	/**
	 * 发送状态:0准备发送 1发送成功 2发送失败
	 */
	private String send_status;
	
	/**
	 * 失败原因
	 */
	private String send_remark;
	
	private String starTime;
	private String endTime;
	
	public String getUser_info_id() {
		return user_info_id;
	}
	public void setUser_info_id(String user_info_id) {
		this.user_info_id = user_info_id;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getSms_send_id() {
		return sms_send_id;
	}
	public void setSms_send_id(String sms_send_id) {
		this.sms_send_id = sms_send_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSend_mobile() {
		return send_mobile;
	}
	public void setSend_mobile(String send_mobile) {
		this.send_mobile = send_mobile;
	}
	public String getSms_send_content() {
		return sms_send_content;
	}
	public void setSms_send_content(String sms_send_content) {
		this.sms_send_content = sms_send_content;
	}
	public String getSend_status() {
		return send_status;
	}
	public void setSend_status(String send_status) {
		this.send_status = send_status;
	}
	public String getSend_remark() {
		return send_remark;
	}
	public void setSend_remark(String send_remark) {
		this.send_remark = send_remark;
	}
	public static Integer getStarNumber() {
		return starNumber;
	}
	public static void setStarNumber(Integer starNumber) {
		SmsSendLogParams.starNumber = starNumber;
	}
	public static Integer getEndNumber() {
		return endNumber;
	}
	public static void setEndNumber(Integer endNumber) {
		SmsSendLogParams.endNumber = endNumber;
	}


}
