package services.Message;

import bd.Database;
import tools.AuthentificationTools;
import tools.ErrorJSON;
import tools.MessageTools;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;



/**
 * 
 * @author momar
 *
 *

Service Name						: AddMessage
Service URL							: /tweet
Service Description					: tweet
Input								: key, message
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user not connected
Progress							: done
Java Classes related to				:AddMessage , AddMessageServlet, ErrorJson, AuthentificationTools, MessageTools


**/

public class AddMessage {
	
	public static JSONObject addMessage(String key, String message){
		
		if(key == null || message == null ) {
			return ErrorJSON.serviceRefused("Wrong arguments", -1);
		}
		
		try {
	
			Connection connection = Database.getMySQLConnection();
			// if user not connected
			
			if(!AuthentificationTools.isConnected(key, connection)) {
				return ErrorJSON.serviceRefused("You are not logged in", 100);
			}
			
			// Get user informations
			String id = AuthentificationTools.getIdUserByKey(key, connection);
			String firstname = AuthentificationTools.getFirstname(id, connection);
			String lastname = AuthentificationTools.getLastname(id, connection);
			String login = AuthentificationTools.getLogin(id, connection);
			
			// Establish a connection with the DB
			MongoDatabase mongoDB = Database.getMongoDBConnection();
			
			//Get to the "messages" collection
			MongoCollection<Document> messages = mongoDB.getCollection("messages");
			
			// Add message to messages
			MessageTools.tweet(id,login,firstname,lastname,message,messages);
			
			return ErrorJSON.serviceAccepted();
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}


