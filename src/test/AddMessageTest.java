package test;



import org.json.JSONObject;

import services.Message.AddMessage;

public class AddMessageTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = AddMessage.addMessage("b7phAuVOUEulgRM1F82VacN0tG09L78y","Birdy is really amazing !");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}