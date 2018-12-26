package ca.poltech.automation.exception;


public class DriverNotAvailableException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public DriverNotAvailableException(String message) {
		
		super(message);
	}

}
