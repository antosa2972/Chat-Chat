package www.project.controller;

import www.project.bean.User;
import www.project.dao.FriendshipDAO;
import www.project.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriendServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);

		UserDAO userDAO = new UserDAO();
		int id1 = userDAO.getUser((String) httpSession.getAttribute("username")).getId();
		int id2 = Integer.parseInt(request.getParameter("userid"));

		FriendshipDAO friendshipDAO = new FriendshipDAO();
		friendshipDAO.createFriendship(id1, id2);

		User user = userDAO.getUser(id2);
		httpSession.setAttribute("friend-uname", user.getUsername());

		getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
	}

}
