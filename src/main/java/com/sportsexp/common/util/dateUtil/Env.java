package com.sportsexp.common.util.dateUtil;

import java.io.InputStream;
import java.util.Properties;

public final class Env extends Properties {

	private static final long serialVersionUID = 1L;
	private static Env instance;

	public static Env getInstance() {
		if (instance != null) {
			return instance;
		} else {
			makeInstance();
			return instance;
		}
	}

	public static synchronized void makeInstance() {
		if (instance == null) {
			instance = new Env();
		}

	}

	private Env() {
		InputStream is = getClass().getResourceAsStream("/db.properties");
		try {
			load(is);
		} catch (Exception ce) {
			System.out.println("读取配置文件失败！，请确定db.properties文件存在");
		}
	}
}
