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

Service Name						: RemoveFriend
Service URL							: /unfollow
Service Description					: unfollow a friend
Input								: key, id_friend
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user not connected, user = friend, friend does not exist, not friend
Progress							: done
Java Classes related to				:AddFriend , AddFriendServlet, ErrorJson, AuthentificationTools; FriendTools


**/
public class RemoveFriend {
	
	public static JSONObject removeFriend(String key, String id_friend ) {
		
		// invalid parameters
		if (key == null || id_friend == null)
			return ErrorJSON.serviceRefused("wrong parameters", -1);
	
		try {
			Connection connection = Database.getMySQLConnection();
			//user is not connected
			if(!AuthentificationTools.isConnected(key,connection))
				return ErrorJSON.serviceRefused("you are not logged in ", 100);
			
			// friend does not exist
			if (!AuthentificationTools.userExistsById(id_friend,connection))
				return ErrorJSON.serviceRefused("The user you are trying to unfollow does not exist", 100);
		
			
			String user_id = AuthentificationTools.getIdUserByKey(key, connection);
		
			// Can't self add
			if(user_id.equals(id_friend))
				return ErrorJSON.serviceRefused("You cannot unfollow yourself", 100);
			
			// Not friend
			if(!FriendTools.alreadyFriend(user_id, id_friend, connection))
				return ErrorJSON.serviceRefused("You cannot unfollow somebody you don't follow already", 100);				
			
			
			
			FriendTools.unfollow(user_id, id_friend, connection);
			return ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
	
	}
}
