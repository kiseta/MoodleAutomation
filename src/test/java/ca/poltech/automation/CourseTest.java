package ca.poltech.automation;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import ca.poltech.automation.authentication.Authentication;
import ca.poltech.automation.authentication.MoodleAuthentication;
import ca.poltech.automation.util.Configuration;
import ca.poltech.automation.util.CourseUtil;
import ca.poltech.automation.util.DriverFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseTest {

	private static WebDriver driver;
	private static Authentication auth;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = DriverFactory.getDriver(DriverFactory.CHROME_DRIVER);
		auth = new MoodleAuthentication();
	}

	@Before
	public void before() {
		driver.get(Configuration.INSTANCE.get("moodle.server.address"));
		auth.login(driver, Configuration.INSTANCE.get("moodle.server.valid.username"),
				Configuration.INSTANCE.get("moodle.server.valid.password"));
	}

	@After
	public void after() {
		auth.logout(driver);
	}

	@Test
	public void create() {
		 
		boolean isCreated = CourseUtil.add(driver, 
				Configuration.INSTANCE.get("moodle.server.course.name"),
				Configuration.INSTANCE.get("moodle.server.course.shortName"),
				Configuration.INSTANCE.get("moodle.server.course.startDay"),
				Configuration.INSTANCE.get("moodle.server.course.startMonth"),
				Configuration.INSTANCE.get("moodle.server.course.startYear"),
				Configuration.INSTANCE.get("moodle.server.course.endDay"),
				Configuration.INSTANCE.get("moodle.server.course.endMonth"),
				Configuration.INSTANCE.get("moodle.server.course.endYear"),
				Configuration.INSTANCE.get("moodle.server.course.summary"));
		
		assertTrue(isCreated);
	} 

	@Test
	public void remove() {

		 assertTrue(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DriverFactory.quitDriverGracefully();
	}
}
