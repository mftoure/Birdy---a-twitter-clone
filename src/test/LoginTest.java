package test;


import org.json.JSONObject;

import services.Authentification.Login;

public class LoginTest {
	
	public static void main(String[] args) {
		JSONObject login = new JSONObject();
		try {
			login = Login.login("tom1", "tom1MDP");
			System.out.println(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}