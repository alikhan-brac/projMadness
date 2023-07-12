package qa.restapi.constants;

import lombok.experimental.UtilityClass;
import qa.restapi.util.JsonDataPuller;

@UtilityClass
public class Constants {
	public static final String ID_ATTRIBUTE_OF_POST="id";
	public static final int POST_ID_FOR_STEP2 = Integer
			.valueOf(JsonDataPuller.dataPull(FilePaths.TEST_DATA, "/postIDToGetSecondStep"));
	public static final int POST_ID_FOR_STEP3 = Integer
			.valueOf(JsonDataPuller.dataPull(FilePaths.TEST_DATA, "/postIDToGetThirdStep"));
	public static final String USERID_FOR_DATA_COMPARE = JsonDataPuller.dataPull(FilePaths.TEST_DATA,
			"/userIdToCompare/id");
	public static final int USER_ID_FOR_STEP6 = Integer.valueOf(USERID_FOR_DATA_COMPARE);
}
