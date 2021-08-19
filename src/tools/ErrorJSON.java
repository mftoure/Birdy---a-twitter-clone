package tools;

import org.json.JSONObject;
import org.json.JSONException;

public class ErrorJSON {

	public static JSONObject serviceRefused(String message, int codeErreur) {
		JSONObject json = new JSONObject();
		try {
			json.put("message", message);
			json.put("code", codeErreur);
			
		}
		catch(JSONException e) {
			System.out.println("exception");
		}
		return json;
	}
	
	public static JSONObject serviceAccepted() {

		JSONObject json = new JSONObject();
		try {
			json.put("code", "OK");
			
		}
		catch(JSONException e) {
			System.out.println("exception");
		}
		return json;
			

	}
}
