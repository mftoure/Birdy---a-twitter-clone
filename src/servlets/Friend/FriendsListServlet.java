package servlets.Friend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import services.Friend.FriendsList;

/**
 * Servlet implementation class FriendsListServlet
 */
@WebServlet("/FriendsListServlet")
public class FriendsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String login = request.getParameter("login");
		String key = request.getParameter("key");
		
		JSONObject retour = FriendsList.friendsList(login, key);
		
		response.getWriter().print(retour);
		
	}

	

}
