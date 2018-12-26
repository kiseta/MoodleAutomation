package ca.poltech.automation.authentication;

import org.openqa.selenium.WebDriver;

/**
 * 
 * Manages the user sessions for the server
 * @author: Tatyana Kiseleva
 *
 */
public interface Authentication {

	public boolean login(WebDriver driver, String userName, String password);

	public boolean logout(WebDriver driver);

	public boolean retrievePassword(WebDriver driver, String email);
	
	public boolean loginAsGuest();

}
