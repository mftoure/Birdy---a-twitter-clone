package services.Authentification;
import org.json.JSONObject;

import tools.ErrorJSON;
import tools.AuthentificationTools;
import org.json.JSONException;



/**
 * 
 * @author momar
 *

Service Name						: Login
Service URL							: /auth 
Service Description					: user logging in
Input								: login, password
Output								: key
Example of output					: {key:..}
Possible errors                     : invalid or incorrect parameters
Progress							: done
Java Classes related to				:Login , LoginServlet, ErrorJson, AuthentificationTools
*/

public class Login {
	
	public static JSONObject login(String login, String password) {
		
		//Checking the validity of the parameters
		if(login == null || password == null) {
			return ErrorJSON.serviceRefused("wrong arguments", -1);
		
		}
		
		try {
		
			//Checking in the DB if the user exists
			
			boolean userExists = AuthentificationTools.userExists(login);
			
			if (!userExists) 
				return ErrorJSON.serviceRefused("Unknown user", 100);
			
			// checking if the password is correct
			boolean goodPassword = AuthentificationTools.checkPassword(login, password);
			
			
			if(!goodPassword)
					return ErrorJSON.serviceRefused("Wrong password", 100);
			
			
			// get user id
			String id_user = AuthentificationTools.getIdUser(login);
			
			JSONObject retour = new JSONObject();
			
			// Create a new session in the database
			
			String key = AuthentificationTools.insertSession(id_user,false);
			
			retour.put("key", key);
			
			return retour;
			
		}catch(JSONException e) {
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
	}
}
