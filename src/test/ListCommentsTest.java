package test;


import org.json.JSONObject;

import services.Comments.ListComments;

public class ListCommentsTest {
	
	public static void main(String[] args) {
		JSONObject comments = new JSONObject();
		try {
			comments = ListComments.listComments("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","5ea25a93d22ae51d0ccc1e27");
			System.out.println(comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}