package qa.restapi.constants;

import lombok.experimental.UtilityClass;
import qa.restapi.util.JsonDataPuller;

@UtilityClass
public class ApiUrl {
	
	public static final String BASE_URI = JsonDataPuller.dataPull(FilePaths.CONFIG_DATA, "/apiurl");
}
