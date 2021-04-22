package www.project.dao;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import www.project.bean.Friendship;
import www.project.bean.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public boolean checkUserFriends(int userId) {

		// Configure Hibernate
		Configuration configuration = new Configuration().configure();

		// Create SessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Get Session object
		Session session = sessionFactory.openSession();

		// SQL query
		String hql = "SELECT * FROM friendships WHERE user1 = :userId";
		NativeQuery<Friendship> query = session.createNativeQuery(hql, Friendship.class);
		query.setParameter("userId", userId);

		Friendship friendship = query.setMaxResults(1).uniqueResult();

		if (friendship == null) {
			System.out.println("\n\n User not Found \n");
			session.close();
			sessionFactory.close();
			return true;
		}

		session.close();
		sessionFactory.close();
		return false;
	}

	public boolean checkUserLogin(String userName, String password) {

		// Configure Hibernate
		Configuration configuration = new Configuration().configure();

		// Create SessionFactory
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		// Get Session object
		Session session = sessionFactory.openSession();

		// SQL query
		String hql = "SELECT * FROM user WHERE username = :userName";
		NativeQuery query = session.createNativeQuery(hql, User.class);
		query.setParameter("userName", userName);

		User existingUser = (User) query.setMaxResults(1).uniqueResult();

		if (existingUser == null) {
			System.out.println("\n\n User not Found \n");
			session.close();
			sessionFactory.close();
			return true;
		} else {
			if (!existingUser.getPassword().equals(password)) {
				System.out.println("\n\n Wrong Password! \n");
				session.close();
				sessionFactory.close();
				return true;
			}
		}

		session.close();
		sessionFactory.close();
		return false;
	}

	public void createUser(String firstName, String lastName, String username, String email, String password,
						   LocalDate birthDate) {
		try {
			Configuration configuration = new Configuration().configure();
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setBirthDate(birthDate);
			session.save(user);
			transaction.commit();
			System.out.println("\n\n Details Added \n");

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
	}

	public boolean userExists(String userName, String email) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "SELECT username, email FROM user WHERE username=:username OR email=:email";
		NativeQuery query = session.createNativeQuery(hql);
		query.setParameter("username", userName);
		query.setParameter("email", email);

		List res = query.list();

		if (!res.isEmpty()) {
			System.out.println("\n\n User Exists \n");
			session.close();
			sessionFactory.close();
			return true;
		}

		session.close();
		sessionFactory.close();
		return false;
	}

	public User getUser(String userName) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "SELECT * FROM user WHERE username=:username";
		NativeQuery<User> query = session.createNativeQuery(hql, User.class);
		query.setParameter("username", userName);

		User existingUser = query.setMaxResults(1).uniqueResult();
		System.out.println(existingUser.getUsername());

		return existingUser;
	}

	public User getUser(int id) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "SELECT * FROM user WHERE id=:id";
		NativeQuery<User> query = session.createNativeQuery(hql, User.class);
		query.setParameter("id", id);

		User existingUser = query.setMaxResults(1).uniqueResult();
//		System.out.println(existingUser.getUsername());

		return existingUser;
	}

	public List<User> getAllUsers(String username) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();


		String hql = "select * from user";
		NativeQuery<User> query = session.createNativeQuery(hql, User.class);

		return query.getResultList();
	}
	public List<Integer> getFriends(int id){
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();


		String hql = "select user2 from friendships where user1=:id";
		NativeQuery<Integer> query = session.createNativeQuery(hql,Integer.class);
		query.setParameter("id", id);

		return query.getResultList();
	}
}
