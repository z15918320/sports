package com.sportsexp.user.vo;

import java.util.Date;

import com.sportsexp.common.util.DateUtil;

/**
 * *
 * <p>
 * Title:UserInfo
 * </p>
 * <p>
 * Description: 系统用户实体类
 * </p>
 * <p>
 * Company: sportsexp
 * </p>
 * 
 * @author zhijiang.zhang
 * @date 2016年6月8日 下午5:22:02
 */
public class UserInfo {
	/**
	 * 用户ID
	 */
	private String USER_INFO_ID;
	/**
	 * 登录名
	 */
	private String LOGIN_CODE;
	/**
	 * 真实姓名
	 */
	private String USER_NAME;
	/**
	 * 用户昵称
	 */
	private String NICK_NAME;
	/**
	 * 生日
	 */
	private Date BIRTHDAY;	
	private String birthdayStr;
	/**
	 * 姓别 0, 男 , 1 女 2 未知
	 */
	private String GENDER="0";
	/**
	 * 密码
	 */
	private String PASSWORD;
	/**
	 * 公司名称
	 */
	private String COMPANY_NAME;
	/**
	 * 公司地址
	 */
	private String COMPANY_ADDR;
	/**
	 * 用户类型 1. 普通用户, 2. 管理员
	 */
	private String USER_TYPE="1";
	/**
	 * 部门名称
	 */
	private String DEPT_NAME;
	/**
	 * 用户状态: 1, 可用, 0 禁用
	 */
	private String STATUS="1";
	/**
	 * 最后登录时间
	 */
	private Date LAST_LOGIN_TIME;
	private String lastLoginTimeStr;
	/**
	 * 上次登录时间
	 */
	private Date PRE_LOGIN_TIME;
	private String preLoginTimeStr;
	/**
	 * 创建时间
	 */
	private Date CREATE_TIME;
	private String createTimeStr;
	
	public String getLastLoginTimeStr() {
		return lastLoginTimeStr;
	}
	public void setLastLoginTimeStr(String lastLoginTimeStr) {
		this.lastLoginTimeStr = lastLoginTimeStr;
	}
	public String getPreLoginTimeStr() {
		return preLoginTimeStr;
	}
	public void setPreLoginTimeStr(String preLoginTimeStr) {
		this.preLoginTimeStr = preLoginTimeStr;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}
	public String getUSER_INFO_ID() {
		return USER_INFO_ID;
	}
	public void setUSER_INFO_ID(String uSER_INFO_ID) {
		USER_INFO_ID = uSER_INFO_ID;
	}
	public String getLOGIN_CODE() {
		return LOGIN_CODE;
	}
	public void setLOGIN_CODE(String lOGIN_CODE) {
		LOGIN_CODE = lOGIN_CODE;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public Date getLAST_LOGIN_TIME() {
		return LAST_LOGIN_TIME;
	}
	public void setLAST_LOGIN_TIME(Date lAST_LOGIN_TIME) {
		if(null!=lAST_LOGIN_TIME){
			lastLoginTimeStr = DateUtil.formatDateToStr(lAST_LOGIN_TIME);
		}
		LAST_LOGIN_TIME = lAST_LOGIN_TIME;
	}
	public Date getPRE_LOGIN_TIME() {
		return PRE_LOGIN_TIME;
	}
	public void setPRE_LOGIN_TIME(Date pRE_LOGIN_TIME) {
		if(null!=pRE_LOGIN_TIME){
			preLoginTimeStr = DateUtil.formatDateToStr(pRE_LOGIN_TIME);
		}
		PRE_LOGIN_TIME = pRE_LOGIN_TIME;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(Date cREATE_TIME) {
		if(null!=cREATE_TIME){
			createTimeStr = DateUtil.formatDateToStr(cREATE_TIME);
		}
		CREATE_TIME = cREATE_TIME;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public Date getBIRTHDAY() {
		return BIRTHDAY;
	}
	public void setBIRTHDAY(Date bIRTHDAY) {
		if(null!=bIRTHDAY){
			birthdayStr = DateUtil.formatDateYMDToStr(bIRTHDAY);
		}
		BIRTHDAY = bIRTHDAY;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}
	public String getCOMPANY_ADDR() {
		return COMPANY_ADDR;
	}
	public void setCOMPANY_ADDR(String cOMPANY_ADDR) {
		COMPANY_ADDR = cOMPANY_ADDR;
	}
	public String getUSER_TYPE() {
		return USER_TYPE;
	}
	public void setUSER_TYPE(String uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}
	public String getNICK_NAME() {
		return NICK_NAME;
	}
	public void setNICK_NAME(String nICK_NAME) {
		NICK_NAME = nICK_NAME;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
	}
	
}
