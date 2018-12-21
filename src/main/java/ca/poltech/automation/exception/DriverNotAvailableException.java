package ca.poltech.automation.exception;

public class DriverNotAvailableException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	public DriverNotAvailableException(String message) {
		super(message);
	}
}