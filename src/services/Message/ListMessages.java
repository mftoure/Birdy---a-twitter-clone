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

Service Name						: ListMessages
Service URL							: /tweets
Service Description					: tweets
Input								: key, idUser
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user not connected, idUser does not exist, idUser has no tweets
Progress							: done
Java Classes related to				:ListMessages , ListMessageServlet, ErrorJson, AuthentificationTools, MessageTools


**/

public class ListMessages {
	
	public static JSONObject listMessages(String key, String idUser){
		
		if(key == null || idUser == null ) {
			return ErrorJSON.serviceRefused("Wrong arguments", -1);
		}
		
		try {
	
			Connection connection = Database.getMySQLConnection();
			// if user not connected
			
			if(!AuthentificationTools.isConnected(key, connection)) {
				return ErrorJSON.serviceRefused("You are not logged in", 100);
			}
			
			if(!AuthentificationTools.userExistsById(idUser, connection)) {
				return ErrorJSON.serviceRefused("User does not exist", 100);
			}

			
			// Establish a connection with the DB
			MongoDatabase mongoDB = Database.getMongoDBConnection();
			
			//Get to the "messages" collection
			MongoCollection<Document> messages = mongoDB.getCollection("messages");
			
			// return list of messages
			
			
			return MessageTools.listTweets(idUser,messages);
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}


