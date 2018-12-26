package ca.poltech.automation.exception;

import org.apache.log4j.Logger;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DriverNotAvailableException.class);

	public AuthenticationException(String message) {
		super(message);
		logger.error(message);
	}

}
