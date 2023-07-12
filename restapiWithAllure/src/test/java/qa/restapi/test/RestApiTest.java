package qa.restapi.test;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import qa.restapi.model.Post;
import qa.restapi.steps.CreatePostSteps;
import qa.restapi.steps.ListPostSteps;
import qa.restapi.steps.ListUserSteps;
import qa.restapi.steps.ReadPostSteps;
import qa.restapi.steps.ReadUserSteps;
import qa.restapi.steps.ResponseSteps;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;
import qa.restapi.util.LipsumGenerator;
import qa.restapi.util.UserToCompare;

public class RestApiTest {

	@Test(priority = 1, description = "get api_data for for all posts")
	public void getAllPost() {
		ListPostSteps listPostSteps = new ListPostSteps();
		Response response = listPostSteps.listPost(Constants.ENDPOINT_COMMON);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_OK);
		ResponseSteps.isResponseBodyInJson(response, Constants.CONTENT_TYPE_JSON);
		listPostSteps.checkListInCorrectOrder(listPostSteps.getListOfId(response));
	}

	@Test(priority = 2, description = "get post with id=99 ")
	public void getPostID99() {
		ReadPostSteps readPostSteps = new ReadPostSteps();
		Response response = readPostSteps.readPost(Constants.ENDPOINT_STEP2);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_OK);
		readPostSteps.checkPostInformationIsCorrect(response.as(Post.class),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToCompare"),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDToCompare"));
	}

	@Test(priority = 3, description = "get post with id=150")
	public void getPostID150() {
		ReadPostSteps readPostSteps = new ReadPostSteps();
		Response response = readPostSteps.readPost(Constants.ENDPOINT_STEP3);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_NOT_FOUND);
		ResponseSteps.checkIfResponseBodyIsEmpty(response, Constants.CONTENT_EMPTY);
	}

	@Test(priority = 4, description = "create a new post ")
	public void createNewPost() {
		String postTitle = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		String postBody = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		int userId = Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep"));
		Post postToCreatePost = new Post();
		postToCreatePost.setUserId(userId);
		postToCreatePost.setTitle(postTitle);
		postToCreatePost.setBody(postBody);

		CreatePostSteps createPostStep = new CreatePostSteps();
		Response response = createPostStep.createPost(postToCreatePost, Constants.ENDPOINT_COMMON);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_CREATED);
		createPostStep.checkPostInformationIsCorrectAsPerCreation(response.getBody().as(Post.class), userId, postTitle,
				postBody);
	}

	@Test(priority = 5, description = "get api_data for all users")
	public void getAllUsers() {
		ListUserSteps listUserSteps = new ListUserSteps();
		Response response = listUserSteps.listUser(Constants.ENDPOINT_COMMON);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_OK);
		ResponseSteps.isResponseBodyInJson(response, Constants.CONTENT_TYPE_JSON);
		UserToCompare.checkUserInformationIsCorrect(listUserSteps.getUserID5FromList(response),
				UserToCompare.getUserID5FromTestData());

	}

	@Test(priority = 6, dependsOnMethods = "getAllUsers", description = "check data of user ID=5 ")
	public void getUserID5() {
		ReadUserSteps readUserSteps = new ReadUserSteps();
		Response response = readUserSteps.readPost(Constants.ENDPOINT_STEP6);
		ResponseSteps.isResponseCodeAsRequired(response, HttpStatus.SC_OK);
		UserToCompare.checkUserInformationIsCorrect(readUserSteps.getUserID5FromResponse(response),
				UserToCompare.getUserID5FromTestData());
	}

}
