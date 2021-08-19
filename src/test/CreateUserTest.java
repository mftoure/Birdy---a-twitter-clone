package test;

import org.json.JSONObject;

import services.User.CreateUser;

public class CreateUserTest {
	public static void main(String[] args) {
		JSONObject user = new JSONObject();
		try {
			user = CreateUser.createUser("tom1", "tom1MDP","Tom","Dubois");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
