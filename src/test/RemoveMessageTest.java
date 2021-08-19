package test;



import org.json.JSONObject;

import services.Message.RemoveMessage;

public class RemoveMessageTest {
	
	public static void main(String[] args) {
		JSONObject add = new JSONObject();
		try {
			add = RemoveMessage.removeMessage("LlR7ax7jmHo6SGf9ReAB3pO6m5yb2cCv","5ea13e323ee2dc5819febcdf");
			System.out.println(add);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}