package qa.restapi.steps;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.constants.Constants;
import qa.restapi.constants.Endpoints;
import qa.restapi.model.User;

public class ListUserSteps extends BaseSteps {

	@Step("Get list of users")
	public Response listUser() {
		return commonRequestSpecification().get(Endpoints.ENDPOINT_USERS);
	}

	@Step("get data from response for user id=5")
	public User getUserID5FromList(Response response) {
		User[] userList = response.as(User[].class);
		Optional<User> optionalUser = Arrays.stream(userList)
				.filter(p -> p.getId().equals(Constants.USERID_FOR_DATA_COMPARE)).findAny();
		assertTrue(optionalUser.isPresent(),"Respective user doesn't exist in response");
		User userFromApi = optionalUser.get();
		return userFromApi;
	}

}
