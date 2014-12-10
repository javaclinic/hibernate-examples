package example.hibernate.run;

import java.text.ParseException;
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

import example.hibernate.domain.Category;
import example.hibernate.domain.User;
import example.hibernate.util.HibernateHelper;

/**
 * Simple example of using inherited entities (Category and User inherit from IdentifiableEntity)
 * 
 * @author nevenc
 *
 */
public class MappingSuperClassesExample {

    private static final Logger logger = LoggerFactory.getLogger(MappingSuperClassesExample.class);

    private static SessionFactory sessionFactory = HibernateHelper.getSessionFactory(User.class,Category.class);

    public static void main(String[] args) throws ParseException {

        for (int i=0;i<20;i++) {
            User user = new User(UUID.randomUUID(),"User " + i,"user"+i+"@email.com", new Date(), i%4!=0);
            saveUser(user);
        }
        for (int i=0;i<5;i++) {
            Category category = new Category(UUID.randomUUID(),"category-" + i,"Description of category " + i);
            saveCategory(category);
        }

        for (User user: findAllUsers()) {
            System.out.println(user);
        }
        for (Category category: findAllCategories()) {
            System.out.println(category);
        }

        sessionFactory.close();

    }

    @SuppressWarnings("unchecked")
    private static List<User> findAllUsers() {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Finding all users:");
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from User");
            List<User> result = query.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            logger.warn("Could not list users due to: " + e.getMessage());
            e.printStackTrace();
            if ( tx != null && tx.isActive() ) tx.rollback();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Category> findAllCategories() {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Finding all categories:");
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Category");
            List<Category> result = query.list();
            tx.commit();
            return result;
        } catch (HibernateException e) {
            logger.warn("Could not list categories due to: " + e.getMessage());
            e.printStackTrace();
            if ( tx != null && tx.isActive() ) tx.rollback();
            return null;
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static void saveUser(User user) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Saving user: {}", user);
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            logger.debug("Saved user: {}", user);
        } catch (HibernateException e) {
            logger.warn("Could not save the user due to: " + e.getMessage());
            e.printStackTrace();
            if ( tx != null && tx.isActive() ) tx.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    private static void saveCategory(Category category) {
        Session session = null;
        Transaction tx = null;
        try {
            logger.debug("Saving category: {}", category);
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
            tx.commit();
            logger.debug("Saved category: {}", category);
        } catch (HibernateException e) {
            logger.warn("Could not save the category due to: " + e.getMessage());
            e.printStackTrace();
            if ( tx != null && tx.isActive() ) tx.rollback();
        } finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

}
