package com.sportsexp.activities.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * *
 * <p>
 * Title:MsgReturn
 * </p>
 * <p>
 * Description:短信接口调用返回标识 类
 * </p>
 * <p>
 * Company: sportsexp
 * </p>
 * 
 * @author zhijiang.zhang
 * @date 2016年6月15日 下午12:02:26
 */
public class MsgReturn {
	/**
	 * SUCCESS:成功 ERROR:失败
	 */
	String tag;
	/**
	 * 错误信息,成功则不返回
	 */
	String errorMsg;

	@XmlElement
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@XmlElement
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
