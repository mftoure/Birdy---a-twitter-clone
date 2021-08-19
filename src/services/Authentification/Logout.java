package services.Authentification;
import org.json.JSONObject;

import tools.ErrorJSON;
import tools.AuthentificationTools;
import org.json.JSONException;
import java.sql.Connection;

import bd.Database;


/**
 * 
 * @author momar
 *

Service Name						: Logout
Service URL							: /logout
Service Description					: user logging out
Input								: key
Output								: OK or error code
Example of output					: {"code:"OK"}
Possible errors                     : invalid or incorrect parameters, SQLExceptions..
Progress							: waiting for the DB
Java Classes related to				:Logout , LogoutServlet, ErrorJson, AuthentificationTools
*/

public class Logout {
	
	public static JSONObject logout(String key_session ) {
		
		
		if(key_session == null) {
			return ErrorJSON.serviceRefused("wrong arguments", -1);
		}
		try {
			
			Connection connection = Database.getMySQLConnection();
			
			if(!AuthentificationTools.isConnected(key_session,connection)) {
				return ErrorJSON.serviceRefused("You are not logged in", 100);
			}
			AuthentificationTools.deleteSession(key_session, connection);
			return ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
	}
}
