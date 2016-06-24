package com.sportsexp.user.pojo;

import java.util.Date;

public class UserParams {
	/**
	 * 起始数
	 */
	private static Integer starNumber=0;
	
	/**
	 * 结束数
	 */
	private static Integer endNumber=0;
	
	/**
	 * 用户ID
	 */
	private String user_info_id;
	/**
	 * 登录名
	 */
	private String login_code;
	/**
	 * 真实姓名
	 */
	private String user_name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 最后登录时间
	 */
	private Date last_login_time;
	/**
	 * 上次登录时间
	 */
	private Date pre_login_time;
	/**
	 * 用户状态: 1, 可用, 0 禁用
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 姓别 0, 男 , 1 女 2 未知
	 */
	private String gender;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 公司名称
	 */
	private String company_name;
	/**
	 * 公司地址
	 */
	private String company_addr;
	/**
	 * 用户类型 1. 普通用户, 2. 管理员
	 */
	private String user_type;
	/**
	 * 用户昵称
	 */
	private String nick_name;
	
	/**
	 * 部门名称
	 */
	private String dept_name;
	
	private String starTime;
	private String endTime;
	public static Integer getStarNumber() {
		return starNumber;
	}
	public static void setStarNumber(Integer starNumber) {
		UserParams.starNumber = starNumber;
	}
	public static Integer getEndNumber() {
		return endNumber;
	}
	public static void setEndNumber(Integer endNumber) {
		UserParams.endNumber = endNumber;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUser_info_id() {
		return user_info_id;
	}
	public void setUser_info_id(String user_info_id) {
		this.user_info_id = user_info_id;
	}
	public String getLogin_code() {
		return login_code;
	}
	public void setLogin_code(String login_code) {
		this.login_code = login_code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public Date getPre_login_time() {
		return pre_login_time;
	}
	public void setPre_login_time(Date pre_login_time) {
		this.pre_login_time = pre_login_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_addr() {
		return company_addr;
	}
	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	
}
