package qa.restapi.test;

import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Ordering;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import qa.restapi.model.User;
import qa.restapi.util.Constants;
import qa.restapi.util.DataHandler;
import qa.restapi.util.JsonDataPuller;
import qa.restapi.util.RestApiUtil;

public class RestApiTest {
	private static String urlForData = "";
	public static String postTitle = "";
	public static String postBody = "";
	public static User userFromApi;

	@Step("Step#1 : get all posts")
	@Description("get api_data for for all posts")
	@Test(priority = 1)
	public void getAllPost() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post");
		Response responseStep1 = RestApiUtil.getResponse(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep1.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
		List<String> idList = responseStep1.jsonPath().getList("id");
		softAssert.assertTrue(Ordering.natural().isOrdered(idList), "Post id not Ordered");
		softAssert.assertAll();
	}

	@Step("Step#2 : get post ID 99")
	@Description("try to get post with id=99 and check if the response is matching with test_data")
	@Test(priority = 2)
	public void getPostID99() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post")
				+ JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetSecondStep");
		Response responseStep2 = RestApiUtil.getResponse(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep2.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
		JsonPath jsonPathEvaluator2 = responseStep2.jsonPath();
		softAssert.assertEquals(jsonPathEvaluator2.get("id"),
				Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToCompare")),
				"id 99 mismatched");
		softAssert.assertEquals(jsonPathEvaluator2.get("userId"),
				Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDToCompare")),
				"userid 10 mismatched");
		softAssert.assertNotNull(jsonPathEvaluator2.get("title"), "title is empty");
		softAssert.assertNotNull(jsonPathEvaluator2.get("body"), "body is empty");
		softAssert.assertAll();
	}

	@Step("Step#3 : get post ID 150")
	@Test(priority = 3)
	@Description("try to get post with id=150 and check if the response is 404")
	public void getPostID150() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post")
				+ JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetThirdStep");
		Response responseStep3 = RestApiUtil.getResponse(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep3.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code is NOT 404");
		softAssert.assertEquals(responseStep3.getBody().asString(), "{}", "Response body is not empty ");
		softAssert.assertAll();
	}

	@Step("Step#4 : create new post")
	@Description("create a new post and check after that if api is sending data accordingly")
	@Test(priority = 4)
	public void createNewPost() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post");
		Response responseStep4 = RestAssured.given().contentType("application/json").body(RestApiUtil.sendPostRequest())
				.when().post(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep4.getStatusCode(), HttpStatus.SC_CREATED, "Status code is NOT 201");
		JsonPath jsonPathEvaluator4 = responseStep4.jsonPath();
		softAssert.assertNotNull(jsonPathEvaluator4.get("id"), "id is absent");
		softAssert.assertEquals(jsonPathEvaluator4.get("userId").toString(),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep"), "userid mismatched");
		softAssert.assertEquals(jsonPathEvaluator4.get("title"), postTitle, "title mismatched");
		softAssert.assertEquals(jsonPathEvaluator4.get("body"), postBody, "body mismatched");
		softAssert.assertAll();
	}

	@Step("Step#5 : get all user")
	@Description("get api_data for all user ID and match if data of ID=5 is matched")
	@Test(priority = 5)
	public void getAllUsers() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/user");
		Response responseStep5 = RestAssured.get(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep5.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
		softAssert.assertEquals(responseStep5.getContentType().toString(), Constants.CONTENT_TYPE,
				"response body is not in Json");
		List<User> userList = responseStep5.then().extract().body().jsonPath().getList(".", User.class);
		userFromApi = userList.stream().filter(p -> p.getId().equals(Constants.USERID_FOR_DATA)).findAny().orElse(null);
		User userFromTestData = DataHandler
				.userToCompare(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToCompare"));
		softAssert.assertEquals(userFromApi, userFromTestData,
				"Respective user data mismatched between api and testData");
		softAssert.assertAll();
	}

	@Step("Step#6 : get user ID 5")
	@Description("get api_data for user ID 5 and match with test_data")
	@Test(priority = 6, dependsOnMethods = "getAllUsers")
	public void getUserID5() {
		urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/user") + "/" + Constants.USERID_FOR_DATA;
		Response responseStep6 = RestAssured.get(urlForData);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(responseStep6.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
		User specificUserFromApi = responseStep6.getBody().as(User.class);
		softAssert.assertEquals(userFromApi, specificUserFromApi,
				"Respective user data mismatched between api and testData");
		softAssert.assertAll();
	}
}
