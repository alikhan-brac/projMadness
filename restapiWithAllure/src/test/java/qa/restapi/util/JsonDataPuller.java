package qa.restapi.util;

import java.io.File;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonDataPuller {
	private static ISettingsFile dataPull;

	public static String dataPull(String fileName, String field) {
		String fieldValue = null;
		try {
			dataPull = new JsonSettingsFile(new File(fileName));
			fieldValue = dataPull.getValue(field).toString();
		} catch (Exception e) {
			AqualityServices.getLogger()
					.error("FILE :" + fileName + " , OR DATA :" + field + " : IS MISSING. PLEASE CHECK.");
		}
		return fieldValue;
	}
}
