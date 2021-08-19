package tools;

import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;

import org.json.JSONObject;

public class LikesTools {

	public static boolean alreadyLikedIt(String idUser,String idMessage, MongoCollection<Document> messages) {
		//Request
		
		Document query = new Document();
		query.append("_id",new ObjectId(idMessage));
				
		// Find the likes field of idMessage
		MongoCursor<Document> cursor = messages.find(query).iterator();
		Document tweet = cursor.next();
			
		ArrayList<Document> likes = (ArrayList<Document>) tweet.get("likes");
				
		//Find the like with idUser
		
		// if there are no likes
		if(likes == null)
			return false;
		
		// else search for the one with iduser
		for (Document like : likes) {
				
			if(like.get("idUser").toString().equals(idUser)) {
					
				return true;
			}
		}
		return false;
	}



	public static void like(String idUser,String login,String firstname,String lastname,String idTweet ,MongoCollection<Document>  messages) throws Exception{
		
		//find the tweet to comment
		Document findQuery = new Document();
		findQuery.append("_id", new ObjectId(idTweet));
				
		
		//Building the comment
		Document like = new Document();
		like.append("_id",new ObjectId());
		like.append("idUser", idUser);
		like.append("login", login);
		like.append("firstname",  firstname);
		like.append("lastname", lastname);
		
		
		

		// Update the tweet with the comment
		messages.updateOne(findQuery, Updates.addToSet("likes", like));

	}
	
	public static void unLike(String idLike, String idMessage, MongoCollection<Document> messages) throws Exception{
	
		//Request
		Document query = new Document();
		query.append("_id",new ObjectId(idMessage));
				
		//update
		messages.updateOne(query, Updates.pull("likes", new Document().append("_id", new ObjectId(idLike))));
	
	}
	
	
public static JSONObject listLikes(String idMessage, MongoCollection<Document> messages) throws Exception{
		
		Document query = new Document().append("_id", new ObjectId(idMessage));
		
		
		// Find the message with _id == idMessage
		MongoCursor<Document> cursor = messages.find(query).iterator();
		Document tweet = cursor.next();
		
		//get the "likes" field in an array
		ArrayList<Document> likes = (ArrayList<Document>) tweet.get("likes");
		
		JSONObject retour = new JSONObject();
		
		if(likes == null)
			return retour;
		
		
		int i = 0;
		for (Document like : likes) {
			i++;
			retour.put(Integer.toString(i),like);
		}
		
		return retour;
	}

}
