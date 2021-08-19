package test;


import org.json.JSONObject;

import services.Friend.AddFriend;

public class AddFriendTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = AddFriend.addFriend("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","12");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}