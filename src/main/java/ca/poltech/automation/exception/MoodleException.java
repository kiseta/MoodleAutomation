package ca.poltech.automation.exception;

import org.apache.log4j.Logger;

public class MoodleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(MoodleException.class);

	public MoodleException(String message) {
		super(message);
		logger.error(message);
	}

}
