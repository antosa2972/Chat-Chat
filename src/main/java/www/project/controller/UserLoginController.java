package www.project.controller;

import org.hibernate.*;
import www.project.bean.Friendship;
import www.project.bean.User;
import www.project.dao.FriendshipDAO;
import www.project.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class UserLoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username").toLowerCase();
		String password = request.getParameter("password");


		HttpSession httpSession = request.getSession(true);

		try {
			UserDAO userDAO = new UserDAO();
			if (userDAO.checkUserLogin(userName, password)) {
				request.setAttribute("message", "Wrong username or password!");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				if(userDAO.checkUserFriends(userDAO.getUser(userName).getId()) == true) {
					User user = userDAO.getUser(userName);
					httpSession.setAttribute("username", user.getUsername());
					httpSession.setAttribute("friend-uname", "");
					response.sendRedirect("./listUsers.jsp");
				}
				else {
					User user = userDAO.getUser(userName);
					FriendshipDAO friendshipDAO = new FriendshipDAO();
					List<Friendship> list = friendshipDAO.getFriendship(user.getId());
					User firstFriend = userDAO.getUser(list.get(0).getUser1());
					httpSession.setAttribute("username", user.getUsername());
					httpSession.setAttribute("friend-uname", firstFriend.getUsername());
					response.sendRedirect("./main.jsp");
				}
			}

		} catch (HibernateException ex) {
			response.getWriter().append(ex.getMessage());
		}
	}
}
