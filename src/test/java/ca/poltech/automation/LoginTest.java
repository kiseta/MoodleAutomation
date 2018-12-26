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
import ca.poltech.automation.exception.AuthenticationException;
import ca.poltech.automation.util.Configuration;
import ca.poltech.automation.util.DriverFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {

	private static WebDriver driver;
	private static Authentication auth;
	
	private static String validUser = Configuration.INSTANCE.get("moodle.server.valid.username");
	private static String validPassword = Configuration.INSTANCE.get("moodle.server.valid.password");
	
	private static String invalidUser = Configuration.INSTANCE.get("moodle.server.invalid.username");
	private static String invalidPassword = Configuration.INSTANCE.get("moodle.server.invalid.password");

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = DriverFactory.getDriver(DriverFactory.CHROME_DRIVER);
		auth = new MoodleAuthentication();
	}

	@Before
	public void before() {
		driver.get(Configuration.INSTANCE.get("moodle.server.address"));
	}

	@After
	public void after() {

	}

	@Test
	public void login() {
		
		final boolean loginnResult = auth.login(driver, validUser, validPassword);
		assertTrue(loginnResult);

	}

	@Test
	public void logout() {
		assertTrue(auth.logout(driver));
	}

	@Test(expected = AuthenticationException.class)
	public void invalidUser() {

		final boolean loginnResult = auth.login(driver, invalidUser, validPassword);
		assertTrue(loginnResult);
	}
	
	@Test(expected = AuthenticationException.class)
	public void invalidPassword() {

		final boolean loginnResult = auth.login(driver, validUser, invalidPassword);
		assertTrue(loginnResult);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DriverFactory.quitDriverGracefully();
	}
}
