package ca.poltech.automation.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CourseUtil {
	private CourseUtil() {

	}

	public static boolean add(WebDriver driver, String name, String shortName, String startDay, String startMonth,
			String startYear, String endDay, String endMonth, String endYear, String summary) {

		// suppose you are in the dashboard
		WebElement dashboardOpenButton = driver
				.findElement(By.cssSelector("#page-wrapper > header > div > div:nth-child(1) > button"));

		if (dashboardOpenButton.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			dashboardOpenButton.click();
		}

		// call the method to get to the course management page
		openCourseManagementPage(driver);

		// find the link "Create new course " and click on it
		driver.findElement(By.cssSelector("#course-listing > div > div.listing-actions.course-listing-actions > a"))
				.click();

		// fill all the mandatory fields of the course form (fill more if want to)
		WebElement createNewCourse = driver.findElement(By.id("id_fullname"));
		createNewCourse.sendKeys(name);

		driver.findElement(By.id("id_shortname")).sendKeys(shortName);

		// when selecting by visible text, the text must be exactly the same as we can
		// read in the drop down from the website
		Select startDaySelector = new Select(driver.findElement(By.id("id_startdate_day")));
		startDaySelector.selectByVisibleText(startDay);

		Select startMonthSelector = new Select(driver.findElement(By.id("id_startdate_month")));
		startMonthSelector.selectByVisibleText(startMonth);

		Select startYearSelector = new Select(driver.findElement(By.id("id_startdate_year")));
		startYearSelector.selectByVisibleText(startYear);

		Select endDaySelector = new Select(driver.findElement(By.id("id_enddate_day")));
		endDaySelector.selectByVisibleText(endDay);

		Select endMonthSelector = new Select(driver.findElement(By.id("id_enddate_month")));
		endMonthSelector.selectByVisibleText(endMonth);

		Select endYearSelector = new Select(driver.findElement(By.id("id_enddate_year")));
		endYearSelector.selectByVisibleText(endYear);

		driver.findElement(By.id("id_summary_editoreditable")).sendKeys(summary);

		// click on save and return
		driver.findElement(By.id("id_saveandreturn")).click();

		boolean hasErrors = false;

		if (!driver.findElements(By.id("id_error_fullname")).isEmpty()) {
			System.out.println(driver.findElement(By.id("id_error_fullname")).getText());
			hasErrors = true;
		}
		if (!driver.findElements(By.id("id_error_shortname")).isEmpty()) {
			System.out.println(driver.findElement(By.id("id_error_shortname")).getText());
			hasErrors = true;
		}

		// We just test if the course was created (it happens in the list courses
		// screen) if we did not find any errors
		if (!hasErrors) {
			// as test , make sure you can find you course in the list
			WebElement courseTable = driver.findElement(By.id("course-listing"));

			// selecting the course from the available list of course names
			return courseTable.getText().contains(name);
		}

		// if there is a course, return true, otherwise return false

		return false;
	}

	public static boolean delete(WebDriver driver) {
		// page-course-delete

		return false;
	}

	public static boolean enrollUser(WebDriver driver, String courseName) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIME_WAIT_GENERAL_TASKS);

		// suppose you are in the dashboard
		WebElement dashboardOpenButton = driver
				.findElement(By.cssSelector("#page-wrapper > header > div > div:nth-child(1) > button"));

		if (dashboardOpenButton.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
			dashboardOpenButton.click();
		}

		// call the method to get to the course management page
		// (Util.openCourseManagementPage)
		openCourseManagementPage(driver);

		// search the course link by the name you got as argument (courseName) and click
		// on it
		driver.findElement(By.cssSelector("#course-listing > div > ul > li:nth-child(1) > div > a")).click();

		// search for the "Enrolled users " link and click on it
		driver.findElement(By.cssSelector(
				"#course-detail > div > div.listing-actions.course-detail-listing-actions > a:nth-child(3)")).click();

		// find the button "Enroll users" and click on it

		final WebElement enrollElement = wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.cssSelector("#enrolusersbutton-1 > div > input.btn.btn-secondary.m-y-1"))));

		enrollElement.click();

		// iterate the studentsName list and enroll all of them
		List<WebElement> studentsList = driver.findElements(By.className("fullname"));

		if (studentsList.size() > 0) {

			for (int i = 0; i < studentsList.size(); i++) {

				WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(
						"#page-enrol-users > div.user-enroller-panel.yui3-dd-draggable > div > div.uep-content.modal-body > div.uep-ajax-content > div > div.users.list-group > div:nth-child("
								+ (i + 1) + ") > div.options > input"))));
				elem.click();

			}

			// click on finish enrolling
			driver.findElement(By.cssSelector(
					"#page-enrol-users > div.user-enroller-panel.yui3-dd-draggable > div > div.uep-footer.modal-footer > div > input"))
					.click();

			// find and click on the filter button
			driver.findElement(By.id("id_submitbutton")).click();

			// at the end of all the commands, go back to the main dashboard
			WebElement dashboardOpenButton2 = driver
					.findElement(By.cssSelector("#page-wrapper > header > div > div:nth-child(1) > button"));

			if (dashboardOpenButton2.getAttribute("aria-expanded").equalsIgnoreCase("false")) {
				dashboardOpenButton2.click();
			}

			// as test, try to find those names in the enrolled list, if they are there,
			// return true, otherwise false
			if (driver.findElement(By.cssSelector("#region-main > div > div > table")).getText().contains("userName")) {
				return true;
			}

		}
		return false;
	}

	public static boolean openCourseManagementPage(WebDriver driver) {

		// click on site administration
		driver.findElement(By.cssSelector("#nav-drawer > nav.list-group.m-t-1 > a > div")).click();

		// click on the tab Courses
		driver.findElement(By.cssSelector("#region-main > div > div > ul > li:nth-child(3)")).click();

		// click on Manage courses and categories
		driver.findElement(By.cssSelector(
				"#linkcourses > div > div > div > div:nth-child(1) > div:nth-child(2) > ul > li:nth-child(1) > a"))
				.click();

		return driver.getCurrentUrl().contains("course/management.php");

	}
}
