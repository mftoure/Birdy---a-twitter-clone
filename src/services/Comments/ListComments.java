package services.Comments;

import bd.Database;
import tools.AuthentificationTools;
import tools.ErrorJSON;
import tools.MessageTools;
import tools.CommentsTools;

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

Service Name						: ListComments
Service URL							: /comments
Service Description					: comments
Input								: key, idTweet
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, message does not exist,user not connected
Progress							: done
Java Classes related to				:ListComments , ListCommentsServelt, ErrorJson, AuthentificationTools, CommentsTools


**/

public class ListComments {
	
	public static JSONObject listComments(String key, String idTweet){
		
		if(key == null || idTweet == null) {
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
			
			//if messages does not exist
			if(!MessageTools.tweetExists(idTweet, messages))
				return ErrorJSON.serviceRefused("Message does not exist", 100);
			
			// list comments
			return CommentsTools.listComments(idTweet,messages);
			
			
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
