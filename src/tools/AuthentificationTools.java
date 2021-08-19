package tools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import bd.Database;

import java.io.*;

public class AuthentificationTools {
	
	
	public static boolean checkPassword(String login, String password) throws Exception {
		
		boolean goodPassword = false;
		
		
			
		Connection conn = Database.getMySQLConnection();
		
		String query = "SELECT password FROM user WHERE login = ?";
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1,login);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		if(password.equals(rs.getString("password")))
			
			goodPassword = true;
		rs.close();
		st.close();
			
			
		
		
	return goodPassword;
		
	}

	public static boolean userExists(String login) throws Exception{
		
		boolean userExists = false;
		
					
		Connection conn = Database.getMySQLConnection();
		
		String query = "SELECT login FROM user WHERE login = ?";
		PreparedStatement st = conn.prepareStatement(query);
		
		st.setString(1, login);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			userExists = true;
		}
		
		rs.close();
		st.close();
			
			
		
	
		return userExists;
	}
	
	public static boolean userExistsById(String id, Connection conn) throws Exception{
		
		boolean userExists = false;
		
		
		String query = "SELECT id FROM user WHERE id = ?";
		PreparedStatement st = conn.prepareStatement(query);
		
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			userExists = true;
		}
		
		rs.close();
		st.close();
			
			
		
	
		return userExists;
	}
	
	public static String getIdUser(String login) throws Exception{
		
		String id;
		
			
		Connection conn = Database.getMySQLConnection();
		
		String query = "SELECT id FROM user WHERE login = ?";
		
		PreparedStatement st = conn.prepareStatement(query);
		
		st.setString(1, login);
		
		ResultSet rs = st.executeQuery();
		
		
		rs.next();
		id = rs.getString("id");
		rs.close();
		st.close();
			
			
		
	
		return id;
	}
	
	public static String getIdUserByKey(String key, Connection connection) throws Exception{
		
		String id;
		
		String query = "SELECT id FROM session WHERE key_session = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1, key);
		
		ResultSet rs = st.executeQuery();
		
		
		rs.next();
		id = rs.getString("id");
		rs.close();
		st.close();
			
			
		
	
		return id;
	}
	
	public static String getLogin(String id, Connection connection) throws Exception{
		String login;
		
		String query = "SELECT login FROM user WHERE id = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1,id);
		
		ResultSet rs = st.executeQuery();
		
		
		rs.next();
		
		login = rs.getString("login");
		
		rs.close();
		st.close();
			
			
		
	
		return login;
	}
	
	public static String insertSession(String id , boolean root) throws Exception{
		
		String key = OTherTools.getAlphaNumericString(32);
		
		
			
		Connection conn = Database.getMySQLConnection();
		
		String query = "INSERT INTO session VALUES(?,?,CURRENT_TIMESTAMP,?)";
		PreparedStatement st = conn.prepareStatement(query);
		
		
		st.setString(1,key);
		st.setString(2,id);
		st.setBoolean(3,root);
		st.executeUpdate();
			
			
		
		st.close();
		
		
		return key;
	}

	public static void registerUser(String login, String password, String firstname, String lastname) throws Exception {
		
		
			
		Connection conn = Database.getMySQLConnection();
		
		String query = "INSERT INTO user (login, password, firstname, lastname) VALUES (?,?,?,?)";
		
		PreparedStatement st = conn.prepareStatement(query);
		
		st.setString(1,login);
		
		st.setString(2, password);
		st.setString(3,firstname);
		st.setString(4,lastname);
		st.executeUpdate();
		st.close();
		
		
	}


	public static void deleteSession(String key, Connection connection) throws Exception{
		
		
		String query = "DELETE FROM session WHERE key_session = ?";
		PreparedStatement st = connection.prepareStatement(query);
		st.setString(1, key);
		st.executeUpdate();
		st.close();
	}

	
	public static boolean isConnected(String key, Connection connection) throws Exception{
		
		Boolean isConnected = false;
		
		String query = "SELECT * FROM session WHERE key_session = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1,key);
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			isConnected = true;
		}
	
		return isConnected;
	}
	
	public static void deleteUser(String login, String password) throws Exception {
		String query = "DELETE FROM user WHERE login = ? and password = ?";
		
		Connection connection = Database.getMySQLConnection();
		
		PreparedStatement st = connection.prepareStatement(query);
		st.setString(1, login);
		st.setString(2, password);
		st.executeUpdate();
		st.close();
	}
	
	public static String getFirstname(String id, Connection connection) throws Exception{
		
		
		
		String query = "SELECT firstname FROM user WHERE id = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1,id);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		return rs.getString("firstname");
		
		
	}
	
	public static String getLastname(String id, Connection connection) throws Exception{
		
		
		
		String query = "SELECT lastname FROM user WHERE id = ?";
		
		PreparedStatement st = connection.prepareStatement(query);
		
		st.setString(1,id);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		return rs.getString("lastname");
		
		
	}

	public static void updateUser(String idUser, String photo, String bio, Connection connection) throws Exception{
		
		String query = "UPDATE user SET photo = ?,  bio = ? WHERE id = ?";
		PreparedStatement st = connection.prepareStatement(query);
		
		//upload photo en format blob
		File file= new File(photo);
        FileInputStream photoEnByte = new FileInputStream(file);
       
        st.setBinaryStream(1, (InputStream) photoEnByte, (int) (file.length()));
        
		st.setString(2, bio);
		st.setString(3, idUser);
		st.executeUpdate();
	}

}
