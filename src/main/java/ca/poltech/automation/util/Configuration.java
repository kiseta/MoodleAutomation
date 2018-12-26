package ca.poltech.automation.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public enum Configuration {
	INSTANCE;

	private static final String CONFIG_FILE_NAME = "/environment.properties";
	private final Properties config = new Properties();

	// Loading all the information
	Configuration() {

		final Logger logger = Logger.getLogger(Configuration.class.getName());

		try (InputStream in = getClass().getResourceAsStream(CONFIG_FILE_NAME)) {
			config.load(in);

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}

	}

	// get the info (I give a key and need the value)

	public String get(String key) {
		return config.containsKey(key) ? config.getProperty(key) : "";
	}

}
