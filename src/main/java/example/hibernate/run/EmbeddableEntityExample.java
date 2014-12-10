package example.hibernate.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.hibernate.domain.Address;
import example.hibernate.domain.User;
import example.hibernate.util.HibernateHelper;

public class EmbeddableEntityExample {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddableEntityExample.class);

    private static SessionFactory sessionFactory = HibernateHelper.getSessionFactory(User.class);

    public static void main(String[] args) {

        System.out.println("Let's create few users and persist them...");
        for (User user : generateNonPersistedUsers()) {
            saveUser(user);
        }

        System.out.println("Let's list all the users ...");
        for (User user : listUsers()) {
            System.out.println(user);
        }

        sessionFactory.close();

    }

    private static void saveUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Saving user: {}", user);
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            logger.debug("Saved user: {}", user);
        } catch (HibernateException e) {
            logger.warn("Could not persist user due to: " + e.getMessage());
            e.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<User> listUsers() {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Finding all users.");
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from User u");
            List<User> result = query.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static List<User> generateNonPersistedUsers() {
        logger.debug("Generating non-persisted users.");
        List<User> users = new ArrayList<>();

        Address address1 = new Address("1 King Street West", "PH5001", "Toronto", "Ontario", "M4K 1A1", "CA");
        Address address2 = new Address("1 Java Way", "Suite 1001", "Washington", "DC", "20001", "US");

        users.add(new User(null, "Judy Doe", "judy@email.com", new Date(), true, address1));
        users.add(new User(null, "Jada Doe", "jada@email.com", new Date(), false, address2));

        return users;
    }

}
