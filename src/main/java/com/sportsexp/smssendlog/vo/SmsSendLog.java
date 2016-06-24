package com.sportsexp.smssendlog.vo;


import java.util.Date;

import com.sportsexp.common.util.DateUtil;

public class SmsSendLog {
	/**
	 * 主键ID
	 */
	private String SMS_SEND_ID;
	
	/**
	 * 明细主键ID
	 */
	private String SMS_SEND_DETAIL_ID;
	
	/**
	 * 发送人ID
	 */
	private String USER_INFO_ID;
	
	/**
	 * 发送人
	 */
	private String USER_NAME;
	
	/**
	 * 接收手机号码
	 */
	private String SEND_MOBILE;
	/**
	 * 发送内容
	 */
	private String SMS_SEND_CONTENT;
	
	/**
	 * 发送时间
	 */
	private Date SEND_TIME;
	private String sendDateStr;
	
	/**
	 * 发送状态:0准备发送 1发送成功 2发送失败
	 */
	private String SEND_STATUS;
	
	/**
	 * 失败原因
	 */
	private String SEND_REMARK;
	
	
	public String getSMS_SEND_DETAIL_ID() {
		return SMS_SEND_DETAIL_ID;
	}

	public void setSMS_SEND_DETAIL_ID(String sMS_SEND_DETAIL_ID) {
		SMS_SEND_DETAIL_ID = sMS_SEND_DETAIL_ID;
	}

	public String getUSER_INFO_ID() {
		return USER_INFO_ID;
	}

	public void setUSER_INFO_ID(String uSER_INFO_ID) {
		USER_INFO_ID = uSER_INFO_ID;
	}

	public String getSendDateStr() {
		return sendDateStr;
	}

	public void setSendDateStr(String sendDateStr) {
		this.sendDateStr = sendDateStr;
	}

	public String getSMS_SEND_ID() {
		return SMS_SEND_ID;
	}

	public void setSMS_SEND_ID(String sMS_SEND_ID) {
		SMS_SEND_ID = sMS_SEND_ID;
	}

	public String getSEND_MOBILE() {
		return SEND_MOBILE;
	}

	public void setSEND_MOBILE(String sEND_MOBILE) {
		SEND_MOBILE = sEND_MOBILE;
	}

	public String getSMS_SEND_CONTENT() {
		return SMS_SEND_CONTENT;
	}

	public void setSMS_SEND_CONTENT(String sMS_SEND_CONTENT) {
		SMS_SEND_CONTENT = sMS_SEND_CONTENT;
	}

	public Date getSEND_TIME() {
		return SEND_TIME;
	}

	public void setSEND_TIME(Date sEND_TIME) {
		if(null!=sEND_TIME){
			sendDateStr = DateUtil.formatDateToStr(sEND_TIME);
		}
		SEND_TIME = sEND_TIME;
	}

	public String getSEND_STATUS() {
		return SEND_STATUS;
	}

	public void setSEND_STATUS(String sEND_STATUS) {
		SEND_STATUS = sEND_STATUS;
	}

	public String getSEND_REMARK() {
		return SEND_REMARK;
	}

	public void setSEND_REMARK(String sEND_REMARK) {
		SEND_REMARK = sEND_REMARK;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}


}
