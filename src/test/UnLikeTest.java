package test;


import org.json.JSONObject;

import services.Likes.UnLike;

public class UnLikeTest {
	
	public static void main(String[] args) {
		JSONObject unLike = new JSONObject();
		try {
			unLike = UnLike.unLike("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea26ee3eb0a2d20dbf5bf1e","5ea25aaca271b447374d1182");
			System.out.println(unLike);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}