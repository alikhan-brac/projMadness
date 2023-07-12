package qa.restapi.steps;

import static org.testng.Assert.assertTrue;

import java.util.List;

import com.google.common.collect.Ordering;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import qa.restapi.constants.Constants;
import qa.restapi.constants.Endpoints;

public class ListPostSteps extends BaseSteps {

	@Step("Get list of posts")
	public Response listPost() {
		return commonRequestSpecification().get(Endpoints.ENDPOINT_POSTS);
	}

	@Step("get the list of IDs of posts from response")
	public List<String> getListOfId(Response response) {
		List<String> idList = response.jsonPath().getList(Constants.ID_ATTRIBUTE_OF_POST);
		return idList;
	}

	@Step("check if the ID list of posts is in ascending order")
	public void checkListInCorrectOrder(List<String> idListOfPosts) {
		assertTrue(Ordering.natural().isOrdered(idListOfPosts), "Post id not Ordered");
	}

}
