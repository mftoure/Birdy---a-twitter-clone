package services.Friend;
import org.json.JSONException;
import org.json.JSONObject;

import bd.Database;
import tools.ErrorJSON;
import tools.AuthentificationTools;
import java.sql.Connection;
import tools.FriendTools;

/**
 * 
 * @author momar
 *
 *

Service Name						: FriendsList
Service URL							: /friends
Service Description					: list of friends
Input								: user_id,
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user does not exist, user not connected
Progress							: done
Java Classes related to				:FriendsList , FriendsListServlet, ErrorJson, AuthentificationTools, FriendTools


**/
public class FriendsList {
	
	public static JSONObject friendsList(String login, String key ) {
		
		if(login == null || key == null)
			return ErrorJSON.serviceRefused("wrong parameters", -1);
	
		try {
			
			Connection connection = Database.getMySQLConnection();
			// User does not exists
			if(!AuthentificationTools.userExists(login))
				return ErrorJSON.serviceRefused("User does not exists", 100);
			
			// User is not logged in
			if(!AuthentificationTools.isConnected(key, connection))
				return ErrorJSON.serviceRefused("Not Logged in", 100);
			
			String id = AuthentificationTools.getIdUser(login);
			return FriendTools.followers(id,connection);
			
		}
		catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
	
	
	}
}
