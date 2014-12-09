package example.hibernate.run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.hibernate.domain.User;
import example.hibernate.util.HibernateHelper;

public class NamedQueriesExample {

    private static final Logger logger = LoggerFactory.getLogger(NamedQueriesExample.class);

    private static SessionFactory sessionFactory = HibernateHelper.getSessionFactory(User.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws ParseException {

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

        sessionFactory.close();

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

}
