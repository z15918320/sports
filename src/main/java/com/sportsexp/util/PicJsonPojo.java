package com.sportsexp.util;

/**
 * 供应,话题,求购的图片序列化对象
 * @author zhijiang.zhang
 * 2016-04-28 16:21:50 
 */
public class PicJsonPojo {
	private String t_height="1024";
	private String t_url;
	private String t_width="1024";
	
	
	public String getT_height() {
		return t_height;
	}
	public void setT_height(String t_height) {
		this.t_height = t_height;
	}
	public String getT_url() {
		return t_url;
	}
	public void setT_url(String t_url) {
		this.t_url = t_url;
	}
	public String getT_width() {
		return t_width;
	}
	public void setT_width(String t_width) {
		this.t_width = t_width;
	}
}
