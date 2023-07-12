package qa.restapi.steps;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import qa.restapi.constants.ApiUrl;

public abstract class BaseSteps {
	
	public RequestSpecification commonRequestSpecification() {
		RequestSpecification requestSpec = RestAssured.given().baseUri(ApiUrl.BASE_URI);
		return requestSpec;
	}
}
