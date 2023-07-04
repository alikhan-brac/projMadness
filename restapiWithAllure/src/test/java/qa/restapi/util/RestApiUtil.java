package qa.restapi.util;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import qa.restapi.test.RestApiTest;

public class RestApiUtil {

	public static Response getResponse(String urlForData) {
		Response response;
		response = RestAssured.get(urlForData);
		return response;
	}

	public static HashMap<String, String> sendPostRequest() {
		RestApiTest.postTitle = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		RestApiTest.postBody = LipsumGenerator
				.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("title", RestApiTest.postTitle);
		data.put("body", RestApiTest.postBody);
		data.put("userId", JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep"));
		return data;
	}
}
