package test;


import org.json.JSONObject;

import services.Friend.FriendsList;

public class FriendsListTest {
	
	public static void main(String[] args) {
		JSONObject friends = new JSONObject();
		try {
			friends = FriendsList.friendsList("momar49","LYDSA4fpaoXPA7tVxy52VyMHXlCyaHay");
			System.out.println(friends);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}