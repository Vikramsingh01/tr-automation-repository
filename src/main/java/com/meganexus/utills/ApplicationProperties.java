package com.meganexus.utills;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ApplicationProperties {
	private static ApplicationProperties _instance = null;

	private static Properties props = null;

	private ApplicationProperties() {
		props = new Properties();
		try {
			props.load(new FileInputStream(new File("src/main/resources/test.properties")));
			props.load(new FileInputStream(new File("src/main/resources/application.properties")));
		}

		catch (Exception e) {

		}
	}

	public synchronized static ApplicationProperties getInstance() {
		if (_instance == null)
			try {
				_instance = new ApplicationProperties();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return _instance;
	}

	public static String getProperty(String key) {
		getInstance();
		String value = null;
		if (props.containsKey(key))
			value = (String) props.get(key);

		else {
			System.out.println("Value not found in property file");
		}
		return value;
	}

}
