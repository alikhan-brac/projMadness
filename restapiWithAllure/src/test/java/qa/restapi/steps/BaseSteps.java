package qa.restapi.steps;

import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

	public abstract RequestSpecification commonRequestSpecification();
}
