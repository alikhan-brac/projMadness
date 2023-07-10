package qa.restapi.steps;

import static org.testng.Assert.assertEquals;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ResponseSteps {

	@Step("check if the response code is {1}")
	public static void isResponseCodeAsRequired(Response response, int code) {
		assertEquals(response.getStatusCode(), code, "Status code is NOT " + code);
	}

	@Step("check if response body is json")
	public static void isResponseBodyInJson(Response response, String content_type) {
		assertEquals(response.getContentType().toString(), content_type, "response body is not in Json");
	}

	@Step("check if response body is empty")
	public static void checkIfResponseBodyIsEmpty(Response response, String empty_content) {
		assertEquals(response.getBody().asString(), empty_content, "Response body is not empty ");
	}

}
