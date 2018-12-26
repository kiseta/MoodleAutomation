package ca.poltech.automation.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ca.poltech.automation.exception.MoodleException;
import ca.poltech.moodle.model.User;

public class UserUtil {

	public static boolean addNew(WebDriver driver, User user) {

		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_GENERAL_TASKS);

		// make sure you are in the dashboard
		WebElement dashboardOpenButton = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("#page-wrapper > header > div > div:nth-child(1) > button"))));

		if (dashboardOpenButton.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			dashboardOpenButton.click();
		}

		// call the method UserUtil.openUsersManagementPage
		openUsersManagementPage(driver);

		// find the link "Add a new user" and click on it
		WebElement addNewBtn = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("a[href$='user/editadvanced.php?id=-1'"))));

		addNewBtn.click();

		// fill all the mandatory fields of the user form (fill more if want to)
		WebElement userNameField = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("id_username"))));

		userNameField.sendKeys(user.getId());

		// check the Generate password and notify user checkbox
		if (user.isMustCreatePassword()) {
			driver.findElement(By.id("id_createpassword")).click();
		}
		else {
			driver.findElement(By.cssSelector("span[data-passwordunmask=\"displayvalue\"]")).click();
			driver.findElement(By.id("id_newpassword")).sendKeys(user.getPassword());
		}
		
		driver.findElement(By.name("firstname")).sendKeys(user.getFirstName());
		driver.findElement(By.name("lastname")).sendKeys(user.getLastName());
		driver.findElement(By.name("email")).sendKeys(user.getEmail());
		driver.findElement(By.name("city")).sendKeys(user.getCity());

		Select dropdown = new Select(driver.findElement(By.id("id_country")));
		dropdown.selectByVisibleText(user.getCountry());

		driver.findElement(By.id("id_description_editoreditable")).sendKeys(user.getDetails());

		// click on CREATE USER
		driver.findElement(By.id("id_submitbutton")).click();

		// check if fields FirstName, LastName required and UserName is unique.

		if (!driver.findElements(By.id("id_error_firstname")).isEmpty()
				&& driver.findElement(By.id("id_error_firstname")).isDisplayed()) {
			throw new MoodleException(driver.findElement(By.id("id_error_firstname")).getText());
		} else if (!driver.findElements(By.id("id_error_lastname")).isEmpty()
				&& driver.findElement(By.id("id_error_lastname")).isDisplayed()) {
			throw new MoodleException(driver.findElement(By.id("id_error_lastname")).getText());
		} else if (!driver.findElements(By.id("id_error_username")).isEmpty()
				&& driver.findElement(By.id("id_error_username")).isDisplayed()) {
			throw new MoodleException(driver.findElement(By.id("id_error_username")).getText());
		} else if (!driver.findElements(By.id("id_error_newpassword")).isEmpty()
				&& driver.findElement(By.id("id_error_newpassword")).isDisplayed()) {
			throw new MoodleException(driver.findElement(By.id("id_error_newpassword")).getText());
		} else if (!driver.findElements(By.id("id_error_email")).isEmpty()
				&& driver.findElement(By.id("id_error_email")).isDisplayed()) {
			throw new MoodleException(driver.findElement(By.id("id_error_email")).getText());
		}

		final WebElement usersTable = driver.findElement(By.id("users"));
		return usersTable.getText().contains(user.getEmail());

	}

	public static boolean removeUser(WebDriver driver, String userEmail) {

		final WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_GENERAL_TASKS);
		// call the method UserUtil.openUsersManagementPage
		openUsersManagementPage(driver);

		WebElement listUsersLink = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[href$='/admin/user.php']"))));

		listUsersLink.click();

		WebElement usersTable = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("users"))));
		final List<WebElement> trs = usersTable.findElements(By.tagName("tr"));

		for (WebElement tr : trs) {
			if (tr.getText().contains(userEmail)) {
				tr.findElement(By.cssSelector("td:nth-child(6) a:nth-child(1)")).click();

				WebElement confirmDeleteBtn = wait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.cssSelector("#modal-footer > div > div:nth-child(1) > form > button"))));

				confirmDeleteBtn.click();

				// getting the updated users table
				usersTable = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("users"))));

				return !usersTable.getText().contains(userEmail);

			}
		}

		return false;
	}

	public static boolean openUsersManagementPage(WebDriver driver) {

		// suppose you are in the dashboard
		WebDriverWait wait = new WebDriverWait(driver, 15);

		// click on site administration
		driver.findElement(By.cssSelector("#nav-drawer > nav.list-group.m-t-1 > a > div")).click();

		// click on the tab Users
		driver.findElement(By.cssSelector("#region-main > div > div > ul > li:nth-child(2) > a")).click();

		// use your way to check if you are in the correct, return true or false

		return false;

	}
}
