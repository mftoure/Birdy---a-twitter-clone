package servlets.Messages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import services.Message.RemoveMessage;


/**
 * Servlet implementation class RemoveMessageServlet
 */
@WebServlet("/RemoveMessageServlet")
public class RemoveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String key = request.getParameter("key");
		String idTweet = request.getParameter("idTweet");
		
		JSONObject retour = RemoveMessage.removeMessage(key, idTweet);
		
		response.getWriter().print(retour);
	}

}
