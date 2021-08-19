package test;


import org.json.JSONObject;

import services.Message.ListMessages;

public class ListMessagesTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = ListMessages.listMessages("VEld3qQ9NIqmWD1zfjae8XQKaqFnXo3t","17");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}