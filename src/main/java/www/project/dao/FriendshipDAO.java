package www.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import www.project.bean.Friendship;
import www.project.bean.Messages;

import java.util.List;

public class FriendshipDAO {

	public List<Friendship> getFriendship(int user) {


		Configuration configuration = new Configuration().configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();


		String hql = "SELECT * FROM friendships WHERE user1=:user";
		NativeQuery<Friendship> query = session.createNativeQuery(hql, Friendship.class);
		query.setParameter("user", user);

		List<Friendship> list = query.getResultList();

		return list;
	}

	public void createFriendship(int user1, int user2) {

		try {
			// Configure Hibernate
			Configuration configuration = new Configuration().configure();

			// Create SessionFactory
			SessionFactory sessionFactory = configuration.buildSessionFactory();

			// Get Session object
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();

			Friendship friendship = new Friendship();
			friendship.setUser1(user1);
			friendship.setUser2(user2);

			session.save(friendship);
			transaction.commit();
			System.out.println("\n\n Details Added \n");
		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
			System.out.println("error");
		}
	}
}
