package com.sportsexp.common.util;

import java.util.HashMap;

public class ConstantUtils {

	public static String IMAGE_MDR = PropertiesUtils.getConfigPropertiesConfiguration().getString("IMAGE_MDR"); 
	public static String LOCAL_IMAGE_MDR = PropertiesUtils.getConfigPropertiesConfiguration().getString("LOCAL_IMAGE_MDR");
	
	public static HashMap<String,String> RegisterValidateMap = new HashMap<String,String>(); 
	public static HashMap<String,String> UpdatePasswordValidateMap = new HashMap<String,String>(); 
	public static HashMap<String,String> SessionMap = new HashMap<String,String>();

	
}
