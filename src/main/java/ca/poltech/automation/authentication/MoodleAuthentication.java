package ca.poltech.automation.authentication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ca.poltech.automation.util.Constants;

public class MoodleAuthentication extends Authentication {

	private WebDriverWait wait;

	@Override
	public boolean login(WebDriver driver, String userName, String password) {
		wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_LOGIN);
		
		//driver.findElement(By.cssSelector()).click();
		WebElement loginLink = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("span.login a"))));
		loginLink.click();

		WebElement logInUser = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
		logInUser.sendKeys(userName);

		WebElement logInPass = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
		logInPass.click();
		logInPass.sendKeys(password);
		logInUser.submit();

		if (!driver.findElements(By.id("loginerrormessage")).isEmpty()) {

			// think about creating an exception for this situation
			System.out.println(driver.findElement(By.id("loginerrormessage")).getText());
			return false;
		}

		return driver.getTitle().equals("Dashboard");

	}

	@Override
	public boolean logout(WebDriver driver) {
		wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_LOGIN);
		return false;
	}

	@Override
	public boolean retrievePassword(WebDriver driver, String email) {
		// TODO Auto-generated method stub
		return false;
	}

}

