package www.project.controller;

import www.project.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class UserRegisterController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username").toLowerCase();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LocalDate birthDate = LocalDate.parse(request.getParameter("birth-date"));

		try {
			UserDAO userDAO = new UserDAO();
			if (userDAO.userExists(username, email)) {
				request.setAttribute("register_message", "User already Exists!");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				userDAO.createUser(firstName, lastName, username, email, password, birthDate);
				request.setAttribute("register_message", "Success!");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
