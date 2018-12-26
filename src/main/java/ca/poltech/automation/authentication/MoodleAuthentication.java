package ca.poltech.automation.authentication;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ca.poltech.automation.exception.AuthenticationException;
import ca.poltech.automation.util.Constants;

public class MoodleAuthentication implements Authentication {

	final static Logger logger = Logger.getLogger(MoodleAuthentication.class);

	private WebDriverWait wait;

	public boolean login(WebDriver driver, String userName, String password) {

		logger.info("Logging user in " + userName + " with the password: " + password);

		wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_LOGIN);

		WebElement loginLink = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span.login a"))));
		loginLink.click();
		
		WebElement logInUser = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
		logInUser.sendKeys(userName);
		
		WebElement logInPass = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
		logInPass.click();
		
		logInPass.sendKeys(password);
		
		logInUser.submit();


		if (driver.getCurrentUrl().contains("/login/index.php")) {

			final String errorMessageSelector = "div.loginerrors > div";
			final WebElement loginErrorElement = driver.findElement(By.cssSelector(errorMessageSelector));
			final String errMessage = null != loginErrorElement ? loginErrorElement.getText() : "Unknown Error";
			throw new AuthenticationException(errMessage);
		}

		return driver.getTitle().equals("Dashboard");

	}

	public boolean logout(WebDriver driver) {
		wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_GENERAL_TASKS);

		WebElement logoutElement = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.className("usermenu"))));

		logoutElement.click();
		
		//logoutElement.findElement(By.cssSelector("a[href*='/login/logout.php']")).click();
		logoutElement.findElement(By.cssSelector("a.dropdown-item.menu-action[data-title='logout,moodle']")).click();
		

		return !driver.getCurrentUrl().contains("/my/");
	}
	

	public boolean retrievePassword(WebDriver driver, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean loginAsGuest() {
		// TODO Auto-generated method stub
		return false;
	}

}
