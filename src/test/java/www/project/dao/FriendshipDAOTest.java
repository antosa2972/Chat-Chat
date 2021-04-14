package www.project.dao;

import org.junit.jupiter.api.Test;
import www.project.bean.Friendship;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FriendshipDAOTest {

	@Test
	void create() {
		FriendshipDAO friendshipDAO = new FriendshipDAO();
		friendshipDAO.createFriendship(1, 2);
		friendshipDAO.createFriendship(2, 1);
		friendshipDAO.createFriendship(1, 3);
		friendshipDAO.createFriendship(3, 1);

	}

	@Test
	void getFriends() {
		FriendshipDAO friendshipDAO = new FriendshipDAO();
		UserDAO userDAO = new UserDAO();

		List<Friendship> list = friendshipDAO.getFriendship(1);

		for (Friendship friendship: list) {
			System.out.println(friendship.getUser1());
		}
	}

}