package qa.restapi.test;

import org.testng.annotations.Test;

import qa.restapi.crud.PostHandler;
import qa.restapi.crud.UserHandler;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;

public class RestApiTest {
	private static String urlForData = "";

	@Test(priority = 1, description = "get api_data for for all posts")
	public void getAllPost() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post");
		PostHandler postHandler = new PostHandler();
		postHandler.readPost(urlForData);
		postHandler.isResponseCode200();
		postHandler.isResponseBodyInJson();
		postHandler.checkListInCorrectOrder(postHandler.getListOfId());

	}

	@Test(priority = 2, description = "get post with id=99 ")
	public void getPostID99() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post")
				+ JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetSecondStep");
		PostHandler postHandler = new PostHandler();
		postHandler.readPost(urlForData);
		postHandler.isResponseCode200();
		postHandler.checkPostInformationIsCorrect();
	}

	@Test(priority = 3, description = "get post with id=150")
	public void getPostID150() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post")
				+ JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetThirdStep");
		PostHandler postHandler = new PostHandler();
		postHandler.readPost(urlForData);
		postHandler.isResponseCode404();
		postHandler.checkIfResponseBodyIsEmpty();
	}

	@Test(priority = 4, description = "create a new post ")
	public void createNewPost() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post");
		PostHandler postHandler = new PostHandler();
		postHandler.createPost(urlForData);
		postHandler.checkPostInformationIsCorrectAsPerCreation();
	}

	@Test(priority = 5, description = "get api_data for all users")
	public void getAllUsers() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/user");
		UserHandler userHandler = new UserHandler();
		userHandler.readUser(urlForData);
		userHandler.isResponseCode200();
		userHandler.getUserID5FromList();
		userHandler.getUserID5FromTestData();
		userHandler.checkUserInformationIsCorrect();
	}

	@Test(priority = 6, dependsOnMethods = "getAllUsers", description = "check data of user ID=5 ")
	public void getUserID5() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/user") + "/" + Constants.USERID_FOR_DATA;
		UserHandler userHandler = new UserHandler();
		userHandler.readUser(urlForData);
		userHandler.isResponseCode200();
		userHandler.getUserID5FromResponse();
		userHandler.getUserID5FromTestData();
		userHandler.checkUserInformationIsCorrect();
	}

}
