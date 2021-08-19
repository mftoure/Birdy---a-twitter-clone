package test;


import org.json.JSONObject;

import services.Comments.AddComment;

public class AddCommentTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = AddComment.addComment("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea25a93d22ae51d0ccc1e27","PARDON ME !!!!");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}