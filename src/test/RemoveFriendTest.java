package test;


import org.json.JSONObject;

import services.Friend.RemoveFriend;

public class RemoveFriendTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = RemoveFriend.removeFriend("bQq8v6I1ulg7VHnkmXa4gMZa5Y1f0Vm6","7");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}