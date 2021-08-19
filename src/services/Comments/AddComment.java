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

Service Name						: AddComment
Service URL							: /comment
Service Description					: comment
Input								: key, idTweet ,comment
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, message does not exist,user not connected
Progress							: done
Java Classes related to				:AddComment , AddCommentServelt, ErrorJson, AuthentificationTools, CommentsTools


**/

public class AddComment {
	
	public static JSONObject addComment(String key, String idTweet, String comment){
		
		if(key == null || idTweet == null || comment == null) {
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
			
			//if messages does not exist
			if(!MessageTools.tweetExists(idTweet, messages))
				return ErrorJSON.serviceRefused("Message does not exist", 100);
			
			// Add message to messages
			CommentsTools.comment(id,login,firstname,lastname,comment,idTweet,messages);
			
			return ErrorJSON.serviceAccepted();
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
