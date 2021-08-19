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

Service Name						: ListLikes
Service URL							: /likes
Service Description					: 
Input								: key, idLike, idMessage
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, message does not exist,user not connected
Progress							: done
Java Classes related to				: UnLike , UnLikeServelt, ErrorJson, AuthentificationTools, LikesTools


**/

public class ListLikes {
	
	public static JSONObject listLikes(String key, String idTweet){
		
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
			
			// list likes
			return LikesTools.listLikes(idTweet,messages);
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
