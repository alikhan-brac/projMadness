package qa.restapi.util;

public class Constants {
	public static final String CONFIG_DATA = ".\\src\\test\\resources\\config.json";
	public static final String TEST_DATA = ".\\src\\test\\resources\\testData.json";
	public static final String USERID_FOR_DATA = JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToCompare/id");
	public static final String CONTENT_TYPE="application/json; charset=utf-8";
}
