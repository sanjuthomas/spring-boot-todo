package com.example.ml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sanju Thomas
 *
 */
public class MLConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/ml-server-config.properties")));
		} catch (final IOException e) {
			logger.error("Error loading the property file", e);
		}
	}

	public static URIBuilder getURIBuilder() {
		final URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost(properties.getProperty("host")).setPath(properties.getProperty("path"));
		return builder;
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getPassword() {
		return properties.getProperty("password");
	}

}
