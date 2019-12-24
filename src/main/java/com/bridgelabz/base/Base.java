package com.bridgelabz.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Sachin Barpete
 * @purpose Initialize property file
 */
public class Base {

	public int RESPONSE_STATUS_OK = 200;
	public int RESPONSE_STATUS_CREATED = 201;
	public int RESPONSE_STATUS_BAD_REQUEST = 400;
	public int RESPONSE_STATUS_UNATHORIZED = 401;
	public int RESPONSE_STATUS_NOT_FOUND = 404;
	public int RESPONSE_STATUS_INTERNAL_SERVER_ERROR = 500;

	public Properties properties;

	public Base() {
		properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/bridgelabz/config/config.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
