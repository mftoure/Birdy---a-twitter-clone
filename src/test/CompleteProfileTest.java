package test;


import org.json.JSONObject;

import services.User.CompleteProfile;

public class CompleteProfileTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = CompleteProfile.completeProfile("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","/home/momar/Images/banniere.jpg","I am a fun guy");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}