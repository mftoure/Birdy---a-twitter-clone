package tools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import org.json.JSONObject;

public class FriendTools {


	public static void follow(String id, String id_friend, Connection connection) throws Exception {
		
		
		String query = "INSERT INTO friendship VALUES (?,?,CURRENT_TIMESTAMP)";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1, id);
		st.setString(2,id_friend);
		
		st.executeUpdate();
		
		st.close();
	}


	public static Boolean alreadyFriend(String id, String id_friend, Connection connection ) throws Exception{
		
		String query = "SELECT * FROM friendship WHERE user1 = ? and user2 = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1, id);
		st.setString(2,id_friend);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		st.close();
		
		return false;
	}

	public static void unfollow(String id, String id_friend, Connection connection) throws Exception {
		
		String query = "DELETE FROM friendship WHERE user1 = ? and user2 = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1, id);
		st.setString(2,id_friend);
		
		st.executeUpdate();
	}

	
	public static JSONObject followers(String id, Connection connection) throws Exception{
		
		JSONObject retour = new JSONObject();
		
		String query = "SELECT user2 from friendship WHERE user1 = ?";

		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery(); 
		
		
		while(rs.next()){
			
			String id_friend = rs.getString("user2");
			JSONObject friend = new JSONObject();
			
			
			friend.put("firstname",AuthentificationTools.getFirstname(id_friend, connection));
			friend.put("login",AuthentificationTools.getLogin(id_friend, connection));
			friend.put("lastname",AuthentificationTools.getLastname(id_friend, connection));
			
			retour.put(id_friend, friend);
		}
		
		return retour;
		
	}
}
