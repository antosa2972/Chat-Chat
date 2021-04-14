package www.project.dao;

import org.junit.jupiter.api.Test;
import www.project.bean.Messages;
import www.project.bean.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessagesDAOTest {

	@Test
	void addMessages() {

		MessagesDAO messagesDAO = new MessagesDAO();
		messagesDAO.newMessage(1, 2, "man");
		messagesDAO.newMessage(1, 2, "cool");
		messagesDAO.newMessage(2, 1, "cool yes hey");
	}

	@Test
	void getMessages() {

		MessagesDAO messagesDAO = new MessagesDAO();
		List<Messages> messages = messagesDAO.getAllMessages(1, 2);

		for (Messages message : messages) {
			System.out.println(message.getMsg());
		}
	}

}