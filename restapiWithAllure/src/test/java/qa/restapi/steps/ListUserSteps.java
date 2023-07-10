package qa.restapi.steps;

import java.util.List;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.model.User;
import qa.restapi.util.Constants;

public class ListUserSteps extends BaseUserSteps {

	@Step("Get list of users")
	public Response listUser(String endpoint) {
		return commonRequestSpecification().get(endpoint);
	}

	@Step("get data from response for user id=5")
	public User getUserID5FromList(Response response) {
		List<User> userList = response.then().extract().body().jsonPath().getList(".", User.class);
		User userFromApi = userList.stream().filter(p -> p.getId().equals(Constants.USERID_FOR_DATA)).findAny()
				.orElse(null);
		return userFromApi;
	}

}
