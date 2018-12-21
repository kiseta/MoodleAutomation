package ca.poltech.automation.util;

import org.openqa.selenium.WebDriver;

import ca.poltech.automation.authentication.Authentication;
import ca.poltech.automation.authentication.MoodleAuthentication;
import ca.poltech.automation.exception.DriverNotAvailableException;

public class Main {

	public static void main(String[] args) throws InterruptedException, DriverNotAvailableException {

		WebDriver driver = null;

		driver = DriverFactory.getDriver(DriverFactory.CHROME_DRIVER);
		driver.get(Constants.SERVER_ADDRESS);

		Thread.sleep(2000);

		Authentication auth = new MoodleAuthentication();

		if (auth.login(driver, Constants.USER_NAME, Constants.USER_PASSWORD)) {
			System.out.println("Login Successful!");
		} else {
			System.err.println("Login failed");
		}

		//Thread.sleep(20000);

		//DriverFactory.quitDriverGracefully();

	}
}
