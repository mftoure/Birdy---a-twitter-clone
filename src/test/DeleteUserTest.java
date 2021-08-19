package test;


import org.json.JSONObject;

import services.User.DeleteUser;;

public class DeleteUserTest {
	
	public static void main(String[] args) {
		JSONObject deconn = new JSONObject();
		try {
			deconn = DeleteUser.deleteUser("huey2", "huey2MDP");
			System.out.println(deconn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}