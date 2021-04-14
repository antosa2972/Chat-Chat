package www.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import www.project.bean.Messages;

import java.sql.Timestamp;
import java.util.List;

public class MessagesDAO {

	public void newMessage(int sender, int receiver, String msg) {

		try {
			// Configure Hibernate
			Configuration configuration = new Configuration().configure();

			// Create SessionFactory
			SessionFactory sessionFactory = configuration.buildSessionFactory();

			// Get Session object
			Session session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction();

			Messages messages = new Messages();
			messages.setReceiver(receiver);
			messages.setSender(sender);
			messages.setMsg(msg);
			messages.setTimestamp(new Timestamp(System.currentTimeMillis()));

			session.save(messages);
			transaction.commit();
			System.out.println("\n\n Details Added \n");

		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
			System.out.println("error");
		}
	}

	public List<Messages> getAllMessages(int sender, int receiver) {
		List<Messages> list;

		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		System.out.println("sender" + sender);
		System.out.println("receiver" + receiver);
		String hql = "SELECT * FROM messages WHERE (sender=:sender AND receiver=:receiver) OR (sender=:receiver2 AND receiver=:sender2)";
		NativeQuery<Messages> query = session.createNativeQuery(hql, Messages.class);
		query.setParameter("sender", sender);
		query.setParameter("receiver", receiver);
		query.setParameter("sender2", sender);
		query.setParameter("receiver2", receiver);

		list = query.getResultList();
		return list;
	}
}
