package test;


import org.json.JSONObject;

import services.Authentification.Logout;

public class LogoutTest {
	
	public static void main(String[] args) {
		JSONObject logout = new JSONObject();
		try {
			logout = Logout.logout("IVMco9t4iebRWY3mv2y7IkrKCXyiWlQx");
			System.out.println(logout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}