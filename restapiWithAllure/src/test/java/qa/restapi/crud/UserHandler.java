package qa.restapi.crud;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.model.User;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;

public class UserHandler extends BaseHandler {

	private static Response response;
	private static User userFromApi;
	private static User userFromTestData;

	@Step("Get data from users")
	public void readUser(String url) {
		response = getResponse(url);
	}

	@Step("check if the response code is 200")
	public void isResponseCode200() {
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
	}

	@Step("check if response body is json")
	public void isResponseBodyInJson() {
		assertEquals(response.getContentType().toString(), Constants.CONTENT_TYPE, "response body is not in Json");
	}

	@Step("get data from response for user id=5")
	public void getUserID5FromResponse() {
		userFromApi = response.getBody().as(User.class);
	}
	
	@Step("get data from response for user id=5")
	public void getUserID5FromList() {
		List<User> userList = response.then().extract().body().jsonPath().getList(".", User.class);
		userFromApi = userList.stream().filter(p -> p.getId().equals(Constants.USERID_FOR_DATA)).findAny().orElse(null);
	}

	@Step("get data from test_data for user id=5")
	public void getUserID5FromTestData() {
		ObjectMapper objectMapper = new ObjectMapper();
		userFromTestData = new User();
		try {
			userFromTestData = objectMapper
					.readValue(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToCompare"), User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Step("check user information from api is correct as of test_data for id=5")
	public void checkUserInformationIsCorrect() {
		assertEquals(userFromApi, userFromTestData, "Respective user data mismatched between api and testData");
	}
}
