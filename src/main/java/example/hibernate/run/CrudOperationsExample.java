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

import example.hibernate.domain.User;
import example.hibernate.util.HibernateHelper;

public class CrudOperationsExample {

    private static final Logger logger = LoggerFactory.getLogger(CrudOperationsExample.class);

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

        System.out.println("Let's find a user (id=1) ...");
        User user = findUser(1);

        System.out.println("Let's update the status of the user (id=1) to 'inactive' ...");
        user.setActive(false);
        updateUser(user);

        System.out.println("Let's remove the user (id=5) ...");
        deleteUser(5);

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
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static void updateUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Updating user: {}", user);
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
            logger.debug("Updated user: {}", user);
        } catch (HibernateException e) {
            logger.warn("Could not update user due to: " + e.getMessage());
            e.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<User> listUsers() {
        Session session = null;
        try {
            logger.debug("Finding all users.");
            session = sessionFactory.openSession();
            Query query = session.createQuery("from User u");
            return query.list();
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static User findUser(Integer id) {
        Session session = null;
        try {
            logger.debug("Finding user with id: {}", id);
            session = sessionFactory.openSession();
            User user = (User) session.get(User.class, id);
            logger.debug("Found user with id={}: {}", id, user);
            return user;
        } catch (HibernateException e) {
            logger.warn("Could not find user due to: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static void deleteUser(Integer id) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Deleting user with id: {}", id);
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();
            logger.debug("Deleted user: {}", user);
        } catch (HibernateException e) {
            logger.warn("Could not delete user due to: " + e.getMessage());
            e.printStackTrace();
            if (tx != null && tx.isActive()) tx.rollback();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    private static List<User> generateNonPersistedUsers() {
        logger.debug("Generating non-persisted users.");
        List<User> users = new ArrayList<>();
        users.add(new User(null, "John Doe", "john@email.com", new Date(), true));
        users.add(new User(null, "Jane Doe", "jane@email.com", new Date(), true));
        users.add(new User(null, "Jack Doe", "jack@email.com", new Date(), false));
        users.add(new User(null, "Jill Doe", "jill@email.com", new Date(), true));
        users.add(new User(null, "Jenn Doe", "jenn@email.com", new Date(), false));
        users.add(new User(null, "Jean Doe", "jean@email.com", new Date(), true));
        users.add(new User(null, "Joni Doe", "joni@email.com", new Date(), true));
        return users;
    }

}
