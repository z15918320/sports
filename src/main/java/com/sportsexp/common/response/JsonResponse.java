package com.sportsexp.common.response;

import com.wordnik.swagger.annotations.ApiModelProperty;
 
public class JsonResponse {

 
	@ApiModelProperty(value="默认0为放回成功,错误编码",required=true)
	int errorNo ;
	@ApiModelProperty(value="错误提示",required=true)
	String errorContent ="返回成功";
	@ApiModelProperty(value="返回内容",required=true)
	Object content;
   
	public int getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}
	public String getErrorContent() {
		return errorContent;
	}
	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
}
