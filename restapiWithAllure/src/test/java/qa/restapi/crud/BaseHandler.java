package qa.restapi.crud;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseHandler {

	public Response getResponse(String urlForData) {
		Response response = RestAssured.get(urlForData);
		return response;
	}

	public Response sendPost(String url, HashMap<String, String> data) {
		Response response = RestAssured.given().contentType("application/json").body(data).when().post(url);
		return response;
	}
}
