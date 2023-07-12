package qa.restapi.steps;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseSteps {
	
	@Step("check if the response code is {1}")
	public static void isResponseCodeAsRequired(Response response, int code) {
		assertEquals(response.getStatusCode(), code, "Status code is NOT " + code);
	}

	@Step("check if response body is json")
	public static void isResponseBodyInJson(Response response) {
		assertEquals(response.getContentType().substring(0, 16), ContentType.JSON.toString(), "response body is not in Json");
	}

	@Step("check if response body is empty")
	public static void checkIfResponseBodyIsEmpty(Response response) {
		assertEquals(response.getBody().asString(), "{}", "Response body is not empty ");
	}

}
