package qa.restapi.crud;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Ordering;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;
import qa.restapi.util.LipsumGenerator;

public class PostHandler extends BaseHandler {

	private static Response response;
	private static JsonPath jsonPathEvaluator;
	private static String postTitle = "";
	private static String postBody = "";

	@Step("Get data from posts")
	public void readPost(String url) {
		response = getResponse(url);
	}

	@Step("check if the response code is 200")
	public void isResponseCode200() {
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is NOT 200");
	}

	@Step("check if the response code is 404")
	public void isResponseCode404() {
		assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code is NOT 404");
	}

	@Step("check if the response code is 201")
	public void isResponseCode201() {
		assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Status code is NOT 201");
	}

	@Step("check if response body is json")
	public void isResponseBodyInJson() {
		assertEquals(response.getContentType().toString(), Constants.CONTENT_TYPE, "response body is not in Json");
	}

	@Step("get the list of IDs of posts from response")
	public List<String> getListOfId() {
		List<String> idList = response.jsonPath().getList("id");
		return idList;
	}

	@Step("check if the ID list of posts is in ascending order")
	public void checkListInCorrectOrder(List<String> idListOfPosts) {
		assertTrue(Ordering.natural().isOrdered(idListOfPosts), "Post id not Ordered");
	}

	@Step("check post information is correct as of test_data")
	public void checkPostInformationIsCorrect() {
		jsonPathEvaluator = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(jsonPathEvaluator.get("id"),
				Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToCompare")),
				"id 99 mismatched");
		softAssert.assertEquals(jsonPathEvaluator.get("userId"),
				Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDToCompare")),
				"userid 10 mismatched");
		softAssert.assertNotNull(jsonPathEvaluator.get("title"), "title is empty");
		softAssert.assertNotNull(jsonPathEvaluator.get("body"), "body is empty");
	}

	@Step("check if response body is empty")
	public void checkIfResponseBodyIsEmpty() {
		assertEquals(response.getBody().asString(), "{}", "Response body is not empty ");
	}

	@Step("create post")
	public void createPost(String url) {
		postTitle = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		postBody = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("title", postTitle);
		data.put("body", postBody);
		data.put("userId", JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep"));
		response = sendPost(url, data);
	}

	@Step("check post information is correct as per creation")
	public void checkPostInformationIsCorrectAsPerCreation() {
		jsonPathEvaluator = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertNotNull(jsonPathEvaluator.get("id"), "id is absent");
		softAssert.assertEquals(jsonPathEvaluator.get("userId").toString(),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep"), "userid mismatched");
		softAssert.assertEquals(jsonPathEvaluator.get("title"), postTitle, "title mismatched");
		softAssert.assertEquals(jsonPathEvaluator.get("body"), postBody, "body mismatched");
		softAssert.assertAll();
	}

}
