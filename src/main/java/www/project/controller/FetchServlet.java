package www.project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class FetchServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String friendUserName = request.getParameter("friend-uname").toLowerCase();
		System.out.println(friendUserName);

		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("friend-uname", friendUserName);

		getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
	}

}
