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

Service Name						: RemoveComment
Service URL							: /removecomment
Service Description					: comment
Input								: key, idComment, idMessage
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, user not connected, comment does not e,am I the author of the comment
Progress							: done
Java Classes related to				:RemoveComment , RemoveCommentServelt, ErrorJson, AuthentificationTools, CommentsTools


**/

public class RemoveComment {
	
	public static JSONObject removeComment(String key, String idComment, String idMessage){
		
		if(key == null || idComment == null || key == null) {
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
			
			//if Message does not exist
			if(!MessageTools.tweetExists(idMessage, messages)) {
				
				return ErrorJSON.serviceRefused("Message does not exist", 100);
			}
			//if comment does not exist
			if(!CommentsTools.commentExists(idComment, idMessage,messages))
				return ErrorJSON.serviceRefused("Comment does not exist", 100);
			
			// Get user informations
			String idUser = AuthentificationTools.getIdUserByKey(key, connection);
			if(!CommentsTools.AuthorOfComment(idUser,idComment,idMessage, messages))
				return ErrorJSON.serviceRefused("Not Author of comment", 100);
					
			//Remove comment
			CommentsTools.removeComment(idComment,idMessage, messages);			

			return ErrorJSON.serviceAccepted();
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
