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

Service Name						: RemoveMessage
Service URL							: /untweet
Service Description					: untweet
Input								: key, id_tweet
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user not connected, messages does not exists
Progress							: done
Java Classes related to				:RemoveMessage , RemoveMessageServlet, ErrorJson, AuthentificationTools, MessageTools


**/

public class RemoveMessage {
	
	public static JSONObject removeMessage(String key, String idTweet){
		
		if(key == null || idTweet == null ) {
			return ErrorJSON.serviceRefused("Wrong arguments", -1);
		}
		
		try {
	
			Connection connection = Database.getMySQLConnection();
			// if user not connected
			
			if(!AuthentificationTools.isConnected(key, connection)) {
				return ErrorJSON.serviceRefused("You are not logged in", 100);
			}
			
			
			
			// Establish a connection with the DB
			MongoDatabase mongoDB = Database.getMongoDBConnection();
			
			//Get to the "messages" collection
			MongoCollection<Document> messages = mongoDB.getCollection("messages");
			
			
			if (!MessageTools.tweetExists(idTweet,messages))
					return ErrorJSON.serviceRefused("Message does not exist", 100);
			
			String idUser = AuthentificationTools.getIdUserByKey(key, connection);
			
			if(!MessageTools.authorOfTweet(idUser, idTweet, messages)) {
					return ErrorJSON.serviceRefused("You are not the author of the message", 100);
			}
							
							// Remove message from messages
			MessageTools.unTweet(idTweet,messages);
			
			return ErrorJSON.serviceAccepted();
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}


