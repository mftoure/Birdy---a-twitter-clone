package test;


import org.json.JSONObject;

import services.Likes.ListLikes;

public class ListLikesTest {
	
	public static void main(String[] args) {
		JSONObject likes = new JSONObject();
		try {
			likes = ListLikes.listLikes("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea25a93d22ae51d0ccc1e27");
			System.out.println(likes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}