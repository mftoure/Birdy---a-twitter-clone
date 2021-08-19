package test;


import org.json.JSONObject;

import services.Comments.RemoveComment;

public class RemoveCommentTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = RemoveComment.removeComment("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea25acdfa139a5ac41fc291","5ea25a93d22ae51d0ccc1e27");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}