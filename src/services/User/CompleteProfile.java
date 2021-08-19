package services.User;
import java.sql.Connection;

import org.json.JSONException;
import org.json.JSONObject;

import bd.Database;
import tools.ErrorJSON;
import tools.AuthentificationTools;
/**
 * 
 * @author momar
 *
 *

Service Name						: CompleteProfile
Service URL							: /completeprofile
Service Description					: user profile photo and bio creation
Input								: key, photo, bio
Output								: OK or code erreur
Example of output					: {"wrong arguments":1000}
Possible errors                     : invalid parameters, user not connected, 
Progress							: done
Java Classes related to				:CompleteProfile , CompleteProfileServlet, ErrorJson, AuthentificationTools


**/

public class CompleteProfile {
	
	public static JSONObject completeProfile(String key, String photo, String bio) {
		
		
		// Checking the validity of the parameters
		if(key == null) {
			return ErrorJSON.serviceRefused("wrong arguments", -1);
		}
		
		try {
			// Checking if the user has already signed up or not 
			Connection connection = Database.getMySQLConnection();
			
			if (!AuthentificationTools.isConnected(key,connection)) 
				return ErrorJSON.serviceRefused("user not connected", 100);
			
			
			String idUser = AuthentificationTools.getIdUserByKey(key, connection);
			// update the user informations in data base
				
			AuthentificationTools.updateUser(idUser, photo, bio, connection);
				
			return ErrorJSON.serviceAccepted();
				
			
			
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
			
	
	}
}