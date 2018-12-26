package ca.poltech.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ca.poltech.moodle.model.User;

public class Utilities {
	final static Logger logger = Logger.getLogger(Utilities.class.getName());

	public static List<User> readUsersFromCsv(String csvFileName) {
		List<User> userList = new ArrayList<>();

		try {
			InputStream is = Utilities.class.getResourceAsStream("/"+csvFileName);
			InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			
			for (String readLine; (readLine = reader.readLine()) != null;) {

				String[] splittedLine = readLine.split(",");

				if (splittedLine.length == 9) {
					User user = new User();

					user.setId(splittedLine[0]);
					user.setFirstName(splittedLine[1]);
					user.setLastName(splittedLine[2]);
					user.setEmail(splittedLine[3]);
					user.setCity(splittedLine[4]);
					user.setCountry(splittedLine[5]);
					user.setDetails(splittedLine[6]);
					user.setMustCreatePassword(splittedLine[7].equalsIgnoreCase("true"));
					user.setPassword(splittedLine[8]);

					userList.add(user);
				}
			}

		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}

		return userList;
	}
}
