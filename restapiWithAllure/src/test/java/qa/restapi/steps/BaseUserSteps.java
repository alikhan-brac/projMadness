package qa.restapi.steps;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import qa.restapi.util.Constants;

public class BaseUserSteps extends BaseSteps {
	@Override
	public RequestSpecification commonRequestSpecification() {
		RequestSpecification requestSpec = RestAssured.given().baseUri(Constants.BASE_URI)
				.basePath(Constants.PATH_USERS);
		return requestSpec;
	}
}
