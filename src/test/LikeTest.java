package test;


import org.json.JSONObject;

import services.Likes.Like;

public class LikeTest {
	
	public static void main(String[] args) {
		JSONObject like = new JSONObject();
		try {
			like = Like.like("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea25aaca271b447374d1182");
			System.out.println(like);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}