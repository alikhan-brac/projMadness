package qa.restapi.steps;

import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.constants.Endpoints;
import qa.restapi.model.Post;

public class ReadPostSteps extends BaseSteps {

	@Step("Get specific post")
	public Response readPost(int id) {
		return commonRequestSpecification().pathParam("id", id).get(Endpoints.ENDPOINT_SPECIFIC_POST);
	}

	@Step("check post information is correct as of test_data")
	public void checkPostInformationIsCorrect(Post postFromApi, String id, String userId) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(postFromApi.getId(), Integer.valueOf(id), "id 99 mismatched");
		softAssert.assertEquals(postFromApi.getUserId(), Integer.valueOf(userId), "userid 10 mismatched");
		softAssert.assertNotNull(postFromApi.getTitle(), "title is empty");
		softAssert.assertNotNull(postFromApi.getBody(), "body is empty");
		softAssert.assertAll();
	}
}
