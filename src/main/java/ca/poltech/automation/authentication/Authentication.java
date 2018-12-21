package ca.poltech.automation.authentication;

import org.openqa.selenium.WebDriver;

/**
 * 
 * Manages the user sessions for the server
 * @author Tatyana
 *
 */
public abstract class Authentication {

	public abstract boolean login(WebDriver driver, String userName, String password);

	public abstract boolean logout(WebDriver driver);

	public abstract boolean retrievePassword(WebDriver driver, String email);

}
