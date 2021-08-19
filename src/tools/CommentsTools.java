package tools;

import java.util.Date;
import java.util.GregorianCalendar;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;

import org.json.JSONObject;


public class CommentsTools {
	
	public static void comment(String id,String login,String firstname,String lastname,String comment,String idTweet ,MongoCollection<Document>  messages) throws Exception{
		
		//find the tweet to comment
		Document findQuery = new Document();
		findQuery.append("_id", new ObjectId(idTweet));
				
		
		//Building the comment
		Document commentaire = new Document();
		commentaire.append("_id",new ObjectId());
		commentaire.append("id", id);
		commentaire.append("login", login);
		commentaire.append("firstname",  firstname);
		commentaire.append("lastname", lastname);
		commentaire.append("comment", comment);
		
			//date et heure
		GregorianCalendar calendar = new GregorianCalendar();
		Date todayAndTime = calendar.getTime();
		
		commentaire.append("date", todayAndTime);
		
		
		

		// Update the tweet with the comment
		messages.updateOne(findQuery, Updates.addToSet("commentaires", commentaire));

	}


	public static boolean commentExists(String idComment, String idMessage,MongoCollection<Document> messages) throws Exception{
		
		//Request
		Document query = new Document();
		query.append("_id",new ObjectId(idMessage));
		
		// Find the commentaires field of idMessage
		MongoCursor<Document> cursor = messages.find(query).iterator();
		Document tweet = cursor.next();
		
		ArrayList<Document> Comments = (ArrayList<Document>) tweet.get("commentaires");
		
		//Find the comment with idComment
		
		for (Document comment : Comments) {
			
			if(comment.get("_id").toString().equals(idComment)) {
		
				return true;
			}
		}
		
		return false;	
		
		
	}

	public static boolean AuthorOfComment(String idUser,String idComment, String idMessage,MongoCollection<Document> messages) throws Exception{
		
		//Request
		Document query = new Document();
		query.append("_id",new ObjectId(idMessage));
						
		// Find the message with _id == idMessage
		MongoCursor<Document> cursor = messages.find(query).iterator();
		Document tweet = cursor.next();
		
		//get the commentaires field in an array
		ArrayList<Document> Comments = (ArrayList<Document>) tweet.get("commentaires");
						
		//Find the comment with idComment
						
		for (Document comment : Comments) {
							
			if(comment.get("_id").toString().equals(idComment) && comment.get("id").toString().equals(idUser)) 
						
				return true;
				
		}
						
		return false;
	}	
	
	public static void removeComment(String idComment, String idMessage,MongoCollection<Document> messages) throws Exception {
		
		//Request
		Document query = new Document();
		query.append("_id",new ObjectId(idMessage));
		
		//update
		messages.updateOne(query, Updates.pull("commentaires", new Document().append("_id", new ObjectId(idComment))));
	}


	public static JSONObject listComments(String idMessage, MongoCollection<Document> messages) throws Exception{
		
		Document query = new Document().append("_id", new ObjectId(idMessage));
		
		
		// Find the message with _id == idMessage
		MongoCursor<Document> cursor = messages.find(query).iterator();
		Document tweet = cursor.next();
		
		//get the commentaire field in an array
		ArrayList<Document> comments = (ArrayList<Document>) tweet.get("commentaires");
		
		JSONObject retour = new JSONObject();
		
		if (comments == null)
			return retour;
		
		int i = 0;
		for (Document comment : comments) {
			i++;
			retour.put(Integer.toString(i),comment);
		}
		
		return retour;
	}
	
}
