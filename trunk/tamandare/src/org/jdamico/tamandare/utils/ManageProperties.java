package org.jdamico.tamandare.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class ManageProperties {
	private static ManageProperties INSTANCE;

	public static ManageProperties getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ManageProperties();
		}
		return INSTANCE;
	}

	public String read(String prop) {
		// Read properties file.
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(Constants.PROPERTIES_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(prop);
	}
	
	public String read(String filename, String prop) {
		// Read properties file.
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(prop);
	}
	
	public Properties setProp(String filename, String key, String value){
		Properties properties = new Properties();
		try { 
			properties.setProperty(key, value);
			properties.store(new FileOutputStream(filename), null);
			} catch (IOException e) { } 
		return properties;
	}
}
