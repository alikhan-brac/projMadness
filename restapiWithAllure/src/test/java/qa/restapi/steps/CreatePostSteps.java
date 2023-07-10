package qa.restapi.steps;

import org.testng.asserts.SoftAssert;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import qa.restapi.model.Post;

public class CreatePostSteps extends BasePostSteps {

	@Step("create post")
	public Response createPost(Post post, String endpoint) {
		return commonRequestSpecification().contentType(ContentType.JSON).body(post).post(endpoint);
	}

	@Step("check post information is correct as per creation")
	public void checkPostInformationIsCorrectAsPerCreation(Post createdPost, int userId, String postTitle,
			String postBody) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertNotNull(createdPost.getId(), "id is absent");
		softAssert.assertEquals(createdPost.getUserId(), userId, "userid mismatched");
		softAssert.assertEquals(createdPost.getTitle(), postTitle, "title mismatched");
		softAssert.assertEquals(createdPost.getBody(), postBody, "body mismatched");
		softAssert.assertAll();
	}
}
