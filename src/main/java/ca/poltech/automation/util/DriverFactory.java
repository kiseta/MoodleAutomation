package ca.poltech.automation.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ca.poltech.automation.exception.DriverNotAvailableException;

public class DriverFactory {

	public final static int MOZILA_DRIVER = 0;
	public final static int CHROME_DRIVER = 1;

	private static WebDriver driver;

	public static WebDriver getDriver(int driverType) throws DriverNotAvailableException {

		try {
			if (null == driver) {
				switch (driverType) {
				case MOZILA_DRIVER:
					driver = new FirefoxDriver();
					break;
				case CHROME_DRIVER:
					driver = new ChromeDriver();
					break;
				default:
					driver = null;
				}
			}

		} catch (IllegalStateException ie) {
			throw new DriverNotAvailableException(ie.getMessage());
		}
		return driver;
	}

	/**
	 * Closes the open browser and then quit the driver
	 * 
	 * @throws DriverNotAvailableException
	 */
	public static void quitDriverGracefully() throws DriverNotAvailableException {
		if (null == driver) {
			throw new DriverNotAvailableException("The driver is not available");
		}

		driver.close();
		driver.quit();

	}
}
