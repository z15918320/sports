/*package com.miaotu.huanxin.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.miaotu.common.util.PropertiesHolder;

*//**
 * PropertiesUtils
 * 
 * @author Lynch 2014-09-15
 *
 *//*
public class  PropertiesUtils {
	static PropertiesHolder  propertiesHolder = null;
	public static Properties getProperties() {

		Properties p = new Properties();

		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					"config.properties");

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}
	
	public static Properties getProperties(String fileName) {

//		Properties p = new Properties();

		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(
					fileName);

			p.load(inputStream);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return p;
	}

	public static PropertiesHolder  getPropertiesHolder()
	{
		if(null != propertiesHolder)
		{
			return propertiesHolder;
		}
		Properties p = new Properties();
		propertiesHolder = new PropertiesHolder();
		propertiesHolder.setProperties(p);
		return propertiesHolder;
	}	

}
*/