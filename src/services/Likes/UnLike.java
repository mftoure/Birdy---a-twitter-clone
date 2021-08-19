package services.Likes;

import bd.Database;
import tools.AuthentificationTools;
import tools.ErrorJSON;
import tools.MessageTools;
import tools.LikesTools;


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

Service Name						: UnLike
Service URL							: /unlike
Service Description					: unlike
Input								: key, idLike, idMessage
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, message does not exist,user not connected, did not already like it 
Progress							: done
Java Classes related to				: UnLike , UnLikeServelt, ErrorJson, AuthentificationTools, LikesTools


**/

public class UnLike {
	
	public static JSONObject unLike(String key, String idLike,String idTweet){
		
		if(key == null || idTweet == null) {
			return ErrorJSON.serviceRefused("Wrong arguments", -1);
		}
		
		try {
	
			Connection connection = Database.getMySQLConnection();
			// if user not connected
			
			if(!AuthentificationTools.isConnected(key, connection)) {
				return ErrorJSON.serviceRefused("You are not logged in", 100);
			}
			
			
			// Get user informations
			String idUser = AuthentificationTools.getIdUserByKey(key, connection);
				
			
			// Establish a connection with the DB
			MongoDatabase mongoDB = Database.getMongoDBConnection();
			
			//Get to the "messages" collection
			MongoCollection<Document> messages = mongoDB.getCollection("messages");
			
			//if messages does not exist
			if(!MessageTools.tweetExists(idTweet, messages))
				return ErrorJSON.serviceRefused("Message does not exist", 100);
			
			// if he did not already liked it 
			if(!LikesTools.alreadyLikedIt(idUser, idTweet, messages))
				return ErrorJSON.serviceRefused("You did not like it in the first place", 100);
			// Add message to messages
			LikesTools.unLike(idLike,idTweet,messages);
			
			return ErrorJSON.serviceAccepted();
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
