package qa.restapi.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.model.User;

public class ReadUserSteps extends BaseUserSteps {

	@Step("Get specific user")
	public Response readPost(String endpoint) {
		return commonRequestSpecification().get(endpoint);
	}

	@Step("get data from response for user id=5")
	public User getUserID5FromResponse(Response response) {
		User userFromApi = response.as(User.class);
		return userFromApi;
	}

}
