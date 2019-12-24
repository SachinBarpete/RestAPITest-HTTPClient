package com.bridgelabz.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {

	public static String getValueByJSONPath(JSONObject responseJSON, String jsonPath) {
		Object obj = responseJSON;
		for (String str : jsonPath.split("/"))
			if (!str.isEmpty())
				if (!(str.contains("[") || str.contains("]")))
					obj = ((JSONObject) obj).get(str);
				else if (str.contains("[") || str.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(str.split("\\[")[0]))
							.get(Integer.parseInt(str.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}

}
