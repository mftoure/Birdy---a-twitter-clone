package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import bd.Database;
public class BDTest {
	public static void main (String[] args) throws Exception{
		
		try {
			
			Connection conn = Database.getMySQLConnection();
			System.out.println();
			String query = "SELECT * FROM test";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
				int i = rs.getInt("attt1");
				System.out.println(i);
			}
			rs.close();
			st.close();
		
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		
	}
}
