package qa.restapi.constants;

import lombok.experimental.UtilityClass;
import qa.restapi.util.JsonDataPuller;

@UtilityClass
public class Endpoints {
	
	public static final String ENDPOINT_POSTS = JsonDataPuller.dataPull(FilePaths.CONFIG_DATA, "/post");
	public static final String ENDPOINT_USERS = JsonDataPuller.dataPull(FilePaths.CONFIG_DATA, "/user");
	public static final String ENDPOINT_SPECIFIC_POST = JsonDataPuller.dataPull(FilePaths.CONFIG_DATA, "/specificPost");
	public static final String ENDPOINT_SPECIFIC_USER = JsonDataPuller.dataPull(FilePaths.CONFIG_DATA, "/specificUser");
}
