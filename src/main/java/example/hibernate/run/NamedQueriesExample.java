package example.hibernate.run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class NamedQueriesExample {

    private static final Logger logger = LoggerFactory.getLogger(NamedQueriesExample.class);

    private static SessionFactory sessionFactory = HibernateHelper.getSessionFactory(User.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {

        System.out.println("Let's create few users and persist them...");
        for (User user : generateNonPersistedUsers()) {
            saveUser(user);
        }

        System.out.println("Let's find all users with email %n@email.com...");
        for (User user: findByEmail("%n@email.com")) {
            System.out.println(user);
        }

        System.out.println("Let's find all inactive users...");
        for (User user: findByQueryName("findInactive")) {
            System.out.println(user);
        }

        System.out.println("Let's find all active users...");
        for (User user: findByQueryName("findActive")) {
            System.out.println(user);
        }

        System.out.println("Let's find all newer accounts than 2004-01-01");
        for (User user: findNewer(sdf.parse("2004-01-01"))) {
            System.out.println(user);
        }

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


    @SuppressWarnings("unchecked")
    private static List<User> findByEmail(String email) {
        Session session = null;
        try {
            logger.debug("Executing named query: findByEmail");
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery("findByEmail");
            query.setString("email", email);
            return query.list();
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<User> findByQueryName(String queryName) {
        Session session = null;
        try {
            logger.debug("Executing named query: {}", queryName);
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery(queryName);
            return query.list();
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<User> findNewer(Date since) {
        Session session = null;
        try {
            logger.debug("Executing named query: findNewer");
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery("findNewer");
            query.setTimestamp("since", since);
            return query.list();
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static List<User> generateNonPersistedUsers() throws ParseException {
        logger.debug("Generating non-persisted users.");
        List<User> users = new ArrayList<>();
        users.add(new User(null, "John Doe", "john@email.com", sdf.parse("2001-01-01"), true));
        users.add(new User(null, "Jane Doe", "jane@email.com", sdf.parse("2002-01-01"), true));
        users.add(new User(null, "Jack Doe", "jack@email.com", sdf.parse("2003-01-01"), false));
        users.add(new User(null, "Jill Doe", "jill@email.com", sdf.parse("2004-01-01"), true));
        users.add(new User(null, "Jenn Doe", "jenn@email.com", sdf.parse("2005-01-01"), false));
        users.add(new User(null, "Jean Doe", "jean@email.com", sdf.parse("2010-01-01"), true));
        users.add(new User(null, "Joni Doe", "joni@email.com", sdf.parse("2014-01-01"), true));
        return users;
    }

}
