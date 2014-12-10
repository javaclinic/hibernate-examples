package example.hibernate.run;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.hibernate.domain.User;
import example.hibernate.util.HibernateHelper;

public class OptimisticLockingExample {

    private static final Logger logger = LoggerFactory.getLogger(OptimisticLockingExample.class);

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

        System.out.println("Let's find a user (id=03ec320e-e60a-483b-887e-a6f1f5353db6) ...");
        User firstUser = findUser(UUID.fromString("03ec320e-e60a-483b-887e-a6f1f5353db6"));

        if ( firstUser != null ) {
            System.out.println("Let's update the status of the user (id=03ec320e-e60a-483b-887e-a6f1f5353db6) to 'inactive' ...");
            firstUser.setActive(false);
            updateUser(firstUser);
            System.out.println("User's version: " + firstUser.getVersion());
            firstUser.setEmail("new@email.com");
            updateUser(firstUser);
            System.out.println("User's version: " + firstUser.getVersion());
            firstUser.setEmail("newagain@email.com");
            updateUser(firstUser);
            System.out.println("User's version: " + firstUser.getVersion());
        } else {
            System.out.println("Could not find user (id=03ec320e-e60a-483b-887e-a6f1f5353db6).");
        }

        System.out.println("Let's list all the users again ...");
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
            session = sessionFactory.getCurrentSession();
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

    private static void updateUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Updating user: {}", user);
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            logger.debug("Updated user: {}", user);
        } catch (HibernateException e) {
            logger.warn("Could not update user due to: " + e.getMessage());
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

    private static User findUser(UUID id) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Finding user with id: {}", id);
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            tx.commit();
            logger.debug("Found user with id={}: {}", id, user);
            return user;
        } catch (HibernateException e) {
            logger.warn("Could not find user due to: " + e.getMessage());
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
        users.add(new User(UUID.randomUUID(), "Judy Doe", "judy@email.com", new Date(), true));
        users.add(new User(UUID.randomUUID(), "Jada Doe", "jada@email.com", new Date(), false));
        return users;
    }

}
