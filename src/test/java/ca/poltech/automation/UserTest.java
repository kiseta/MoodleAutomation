package ca.poltech.automation;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
import ca.poltech.automation.exception.MoodleException;
import ca.poltech.automation.util.Configuration;
import ca.poltech.automation.util.DriverFactory;
import ca.poltech.automation.util.UserUtil;
import ca.poltech.automation.util.Utilities;
import ca.poltech.moodle.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

	private static WebDriver driver;
	private static Authentication auth;
	private static List<User> userList;
	private static List<User> addedUserList;

	@BeforeClass
	public static void setUpBeforeClass() {
		driver = DriverFactory.getDriver(DriverFactory.CHROME_DRIVER);
		auth = new MoodleAuthentication();
		userList = Utilities.readUsersFromCsv(Configuration.INSTANCE.get("moodle.mock.users.file.name"));
		addedUserList = new ArrayList<>();
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

		boolean isCreated = false;
		for (User user : userList) {
			isCreated = UserUtil.addNew(driver, user);
			if (isCreated) {
				addedUserList.add(user);
			} else {
				break;
			}
		}

		assertTrue(isCreated);
	}

	@Test(expected = MoodleException.class)
	public void createExisting() {

		boolean isCreated = UserUtil.addNew(driver, userList.get(0));
		assertTrue(isCreated);
	}

	@Test
	public void remove() {
		boolean isRemoved = false;

		for (User user : addedUserList) {
			isRemoved = UserUtil.removeUser(driver, user.getEmail());
			if (!isRemoved) {
				break;
			}
		}

		assertTrue(isRemoved);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DriverFactory.quitDriverGracefully();
	}
}
