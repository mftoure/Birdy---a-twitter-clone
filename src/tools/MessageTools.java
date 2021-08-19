package tools;

import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.*;
import org.json.JSONObject;


public class MessageTools {
	
	public static void tweet(String idUser, String login, String firstname, String lastname, String message, MongoCollection<Document> messages) throws Exception{
		
		// Tweet creation
		Document tweet = new Document();
		tweet.append("idUser", idUser);
		tweet.append("login",login);
		tweet.append("firstname",firstname);
		tweet.append("lastname", lastname);
		tweet.append("message", message);
		
		//date et heure
		GregorianCalendar calendar = new GregorianCalendar();
		Date todayAndTime = calendar.getTime();
		
		tweet.append("date", todayAndTime);
		// Add to the tweets
		messages.insertOne(tweet);
		
	}

	public static void unTweet(String idTweet, MongoCollection<Document> messages) throws Exception {
		
		Document query = new Document();
		query.append("_id", new ObjectId(idTweet));
		
		messages.deleteOne(query);
		
		
	}
	
	public static boolean tweetExists(String idTweet, MongoCollection<Document> messages) throws Exception {
		
		// Request with parameters
	 	Document query = new Document();
	 	query.append("_id", new ObjectId(idTweet));
		
		// questioning the document
		MongoCursor<Document> cursor = messages.find(query).iterator();
		
		
		while(cursor.hasNext()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean authorOfTweet(String idUser, String idTweet, MongoCollection<Document> messages) throws Exception{
		
		//Request with parameters
		Document query = new Document();
		query.append("idUser", idUser);
		query.append("_id", new ObjectId(idTweet));
		// questioning the document
		MongoCursor<Document> cursor = messages.find(query).iterator();
		

		while(cursor.hasNext()) {
			return true;
		}
		
		return false;
	}
	
	public static JSONObject listTweets(String idUser,MongoCollection<Document> messages) throws Exception{
		
		JSONObject retour = new JSONObject(); 
		
		Document query = new Document();
		query.append("idUser", idUser);
		
		MongoCursor<Document> cursor = messages.find(query).iterator();
		
		int i =1;

		while(cursor.hasNext()) {
			
			Document tweet = cursor.next();
			retour.put(Integer.toString(i), tweet);
			i++;
		}
		
	return retour;	
	}
}
