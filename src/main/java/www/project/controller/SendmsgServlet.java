package www.project.controller;

import www.project.bean.User;
import www.project.dao.MessagesDAO;
import www.project.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendmsgServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newMessage = request.getParameter("input-box").toLowerCase();
		System.out.println(newMessage);

		HttpSession httpSession = request.getSession(true);

		UserDAO userDAO = new UserDAO();

		System.out.println((String) httpSession.getAttribute("username"));
		System.out.println((String) httpSession.getAttribute("friend-uname"));

		User sender = userDAO.getUser((String) httpSession.getAttribute("username"));
		User receiver = userDAO.getUser((String) httpSession.getAttribute("friend-uname"));

		MessagesDAO messagesDAO = new MessagesDAO();
		messagesDAO.newMessage(sender.getId(), receiver.getId(), newMessage);

		getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
	}
}
