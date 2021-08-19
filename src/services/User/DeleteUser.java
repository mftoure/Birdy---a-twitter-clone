package services.User;
import org.json.JSONException;
import org.json.JSONObject;

import tools.ErrorJSON;
import tools.AuthentificationTools;
/**
 * 
 * @author momar
 *
 *

Service Name						: DeleteUser
Service URL							: /unregister
Service Description					: user remove
Input								: login, password
Output								: OK or code erreur
Example of output					: {"wrong arguments":1000}
Possible errors                     : invalid parameters, wrong password, user doesn't exists
Progress							: done
Java Classes related to				:DeleteUser , DeleteUserServlet, ErrorJson, AuthentificationTools


**/

public class DeleteUser {
	
	public static JSONObject deleteUser(String login, String password) {
		
		
		if(login == null || password == null) {
			return ErrorJSON.serviceRefused("wrong arguments", -1);
		}
		
		try {
			// Checking if the user has already signed up or not 
			
			if (!AuthentificationTools.userExists(login)) 
				return ErrorJSON.serviceRefused("user doesn't exists", 100);
			
			// Checking if the password is correct
			if(!AuthentificationTools.checkPassword(login, password))
				return ErrorJSON.serviceRefused("wrong password", 100);
				
			
			// remove the user in the DB
				
			AuthentificationTools.deleteUser(login,password);
				 
			return ErrorJSON.serviceAccepted();
				
				
				
			
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
			
	
	}
}
