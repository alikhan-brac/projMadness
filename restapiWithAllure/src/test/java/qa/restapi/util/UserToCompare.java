package qa.restapi.util;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qameta.allure.Step;
import qa.restapi.model.User;

public class UserToCompare {

	@Step("get data from test_data for user id=5")
	public static User getUserID5FromTestData() {
		ObjectMapper objectMapper = new ObjectMapper();
		User userFromTestData = new User();
		try {
			userFromTestData = objectMapper
					.readValue(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToCompare"), User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userFromTestData;
	}

	@Step("check user information from api response is correct as of test_data for id=5")
	public static void checkUserInformationIsCorrect(User userFromApi, User userFromTestData) {
		assertEquals(userFromApi, userFromTestData, "Respective user data mismatched between api and testData");
	}
}
