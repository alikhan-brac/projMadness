package qa.restapi.util;

public class Constants {
	public static final String CONFIG_DATA = ".\\src\\test\\resources\\config.json";
	public static final String TEST_DATA = ".\\src\\test\\resources\\testData.json";
	public static final String USERID_FOR_DATA = JsonDataPuller.dataPuller(TEST_DATA, "/userIdToCompare/id");
	public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
	public static final String CONTENT_EMPTY = "{}";

	public static final String BASE_URI = JsonDataPuller.dataPuller(CONFIG_DATA, "/apiurl");
	public static final String PATH_POSTS = JsonDataPuller.dataPuller(CONFIG_DATA, "/post");
	public static final String PATH_USERS = JsonDataPuller.dataPuller(CONFIG_DATA, "/user");
	public static final String ENDPOINT_COMMON = "/";
	public static final String ENDPOINT_STEP2 = JsonDataPuller.dataPuller(TEST_DATA, "/postIDToGetSecondStep");
	public static final String ENDPOINT_STEP3 = JsonDataPuller.dataPuller(TEST_DATA, "/postIDToGetThirdStep");
	public static final String ENDPOINT_STEP6 = "/" + USERID_FOR_DATA;
}
