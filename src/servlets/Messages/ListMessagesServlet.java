package servlets.Messages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.Message.ListMessages;

/**
 * Servlet implementation class ListMessagesServlet
 */
@WebServlet("/ListMessagesServlet")
public class ListMessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMessagesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String key = request.getParameter("key");
		String id = request.getParameter("id");
		
		JSONObject retour = ListMessages.listMessages(key, id);
		
		response.getWriter().print(retour);
	}

	

}
