package com.example.ml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sanju Thomas
 *
 */
public class MLConfiguration {

	private static final String ML_SERVER_CONFIG_PROPERTIES = "src/main/resources/ml-server-config.properties";
	private static final String TODO_OPTIONS_XML = "src/main/resources/todo-options.xml";
	private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);
	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(ML_SERVER_CONFIG_PROPERTIES)));
		} catch (final IOException e) {
			logger.error("Error loading the property file", e);
		}
	}

	public static URIBuilder getURIBuilder(MLEndpoints endpoint) {
		final URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost(properties.getProperty("host")).setPath(properties.getProperty(endpoint.property()));
		return builder;
	}

	public static String getUsername() {
		return properties.getProperty("username");
	}

	public static String getPassword() {
		return properties.getProperty("password");
	}
	
	public static void loadConfigs() {
		
		try {
			final Path path = Paths.get(TODO_OPTIONS_XML);
			final String stringFromFile = java.nio.file.Files.lines(path).collect(Collectors.joining());
			RequestProcessor.process(RequestBuilder.post(new QueryOptionsPayload("todo-options", stringFromFile)));
		} catch (IOException | URISyntaxException e) {
			logger.error("Error loading the query options file", e);
		}
	}

}
