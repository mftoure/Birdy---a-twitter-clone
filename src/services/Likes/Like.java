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

Service Name						: Like
Service URL							: /like
Service Description					: like
Input								: key, idTweet
Output								: OK or code erreur
Example of output					: 
Possible errors                     : invalid parameters, message does not exist,user not connected, already liked it
Progress							: done
Java Classes related to				:Like , LikeServelt, ErrorJson, AuthentificationTools, LikesTools


**/

public class Like {
	
	public static JSONObject like(String key, String idTweet){
		
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
			String firstname = AuthentificationTools.getFirstname(idUser, connection);
			String lastname = AuthentificationTools.getLastname(idUser, connection);
			String login = AuthentificationTools.getLogin(idUser, connection);
		
				
			
			// Establish a connection with the DB
			MongoDatabase mongoDB = Database.getMongoDBConnection();
			
			//Get to the "messages" collection
			MongoCollection<Document> messages = mongoDB.getCollection("messages");
			
			//if messages does not exist
			if(!MessageTools.tweetExists(idTweet, messages))
				return ErrorJSON.serviceRefused("Message does not exist", 100);
			
			
			if(LikesTools.alreadyLikedIt(idUser, idTweet, messages))
				return ErrorJSON.serviceRefused("Already liked it", 100);
			// Add message to messages
			LikesTools.like(idUser,login,firstname,lastname,idTweet,messages);
			
			return ErrorJSON.serviceAccepted();
			
		}catch(JSONException e) {
			
			return ErrorJSON.serviceRefused(e.getMessage(),100);
		
		}catch(Exception e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		
	}

}
