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

Service Name						: CreateUser
Service URL							: /user
Service Description					: user creation
Input								: login, password, firstname, lastname
Output								: OK or code erreur
Example of output					: {"wrong arguments":1000}
Possible errors                     : invalid parameters
Progress							: done
Java Classes related to				:CreateUser , CreateUserServlet, ErrorJson, AuthentificationTools


**/

public class CreateUser {
	
	public static JSONObject createUser(String login, String password, String firstname, String lastname) {
		
		
		// Checking the validity of the parameters
		if(login == null || password == null || lastname == null || firstname == null) {
			return ErrorJSON.serviceRefused("wrong arguments", -1);
		}
		
		try {
			// Checking if the user has already signed up or not 
			
			if (AuthentificationTools.userExists(login)) 
				return ErrorJSON.serviceRefused("user Already exists", 100);
			
			
			// creation of the user in the DB
				
			AuthentificationTools.registerUser(login,password,firstname,lastname);
				
			return ErrorJSON.serviceAccepted();
				
			
			
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
			
	
	}
}
