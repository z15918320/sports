package com.sportsexp.huanxin.comm;

import com.sportsexp.common.util.PropertiesUtils;

 
/**
 * Constants
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {

	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = "https";
	
	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getConfigPropertiesConfiguration().getString("API_SERVER_HOST");
	// APPKEY
	public static String APPKEY = PropertiesUtils.getConfigPropertiesConfiguration().getString("APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getConfigPropertiesConfiguration().getString("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getConfigPropertiesConfiguration().getString("APP_CLIENT_SECRET");
	
//	// API_SERVER_HOST
//	public static String API_SERVER_HOST = PropertiesUtils.getPropertiesHolder().getProperty("API_SERVER_HOST");
//	// APPKEY
//	public static String APPKEY = PropertiesUtils.getPropertiesHolder().getProperty("APPKEY");
//	// APP_CLIENT_ID
//	public static String APP_CLIENT_ID = PropertiesUtils.getPropertiesHolder().getProperty("APP_CLIENT_ID");
//	// APP_CLIENT_SECRET
//	public static String APP_CLIENT_SECRET = PropertiesUtils.getPropertiesHolder().getProperty("APP_CLIENT_SECRET");
	// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "123456";
}
