package com.axe.SupportedUtils;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonComponent {
	

	public static JSONObject getJsonData(String strDataElementName,String strFileName) {
		try {
			JSONObject JSONFileData = (JSONObject) new JSONParser().parse(new FileReader(System.getProperty("user.dir") +Constants.DATAINPUT_DIR +"\\"+strFileName+".json"));
			JSONObject joTestData = (JSONObject) JSONFileData.get(strDataElementName);
			return joTestData;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
