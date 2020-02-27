package com.ericsson.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
	
	public static final String CONFIG_LOCATION = System.getProperty("keychain.config");
	
	public static final Properties prop = loadProperty();
	
	private static Properties loadProperty(){
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(CONFIG_LOCATION + File.separator + "keychain.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static Properties readProperty(String fileName) {

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(CONFIG_LOCATION + File.separator + fileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
