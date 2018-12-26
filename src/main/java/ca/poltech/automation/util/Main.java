package ca.poltech.automation.util;

import org.openqa.selenium.WebDriver;

import ca.poltech.automation.authentication.Authentication;
import ca.poltech.automation.authentication.MoodleAuthentication;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = null;
		driver = DriverFactory.getDriver(DriverFactory.CHROME_DRIVER);
		driver.get(Constants.SERVER_ADDRESS);

		//its only for the user to see
		Thread.sleep(2000);

		Authentication auth = new MoodleAuthentication();

		if (auth.login(driver, Constants.USER_NAME, Constants.USER_PASSWORD)) {
			
		} else {
			System.err.println("Login failed");
		}

		Thread.sleep(20000);

		DriverFactory.quitDriverGracefully();

	}
}
