package qa.restapi.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.constants.Endpoints;
import qa.restapi.model.User;

public class ReadUserSteps extends BaseSteps {

	@Step("Get specific user")
	public Response readUser(int id) {
		return commonRequestSpecification().pathParam("id", id).get(Endpoints.ENDPOINT_SPECIFIC_USER);
	}

	@Step("get data from response for user id=5")
	public User getUserID5FromResponse(Response response) {
		User userFromApi = response.as(User.class);
		return userFromApi;
	}

}
