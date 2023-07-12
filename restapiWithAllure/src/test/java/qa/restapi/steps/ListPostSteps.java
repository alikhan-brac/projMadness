package qa.restapi.steps;

import static org.testng.Assert.assertTrue;

import java.util.List;

import com.google.common.collect.Ordering;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ListPostSteps extends BasePostSteps {

	@Step("Get list of posts")
	public Response listPost(String endpoint) {
		return commonRequestSpecification().get(endpoint);
	}

	@Step("get the list of IDs of posts from response")
	public List<String> getListOfId(Response response) {
		List<String> idList = response.jsonPath().getList("id");
		return idList;
	}

	@Step("check if the ID list of posts is in ascending order")
	public void checkListInCorrectOrder(List<String> idListOfPosts) {
		assertTrue(Ordering.natural().isOrdered(idListOfPosts), "Post id not Ordered");
	}

}
