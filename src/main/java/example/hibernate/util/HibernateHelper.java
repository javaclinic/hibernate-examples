package example.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HibernateHelper helps initialize SessionFactory
 * 
 * @author nevenc
 *
 */
public class HibernateHelper {

    private static final Logger logger = LoggerFactory.getLogger(HibernateHelper.class);

    /**
     * Initializes SessionFactory object with provided classes
     * 
     * @param classes list of entity classes to map
     * @return session factory object
     */
    public static SessionFactory getSessionFactory(Class<?>... classes) {

        logger.debug("Initializing Hibernate SessionFactory.");

        // Hibernate 4.x
        Configuration configuration = new Configuration();

        // add each annotated class separately
        for (Class<?> clazz : classes) {
            configuration.addAnnotatedClass(clazz);
        }

        // Hibernate 4.0, 4.1, 4.2
        // ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

        // Hibernate 4.3
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        logger.debug("Done initializing Hibernate SessionFactory.");
        return sessionFactory;

    }

}
