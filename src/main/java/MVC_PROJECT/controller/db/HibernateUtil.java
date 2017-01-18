package MVC_PROJECT.controller.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.io.File;

/**
 * Created by innopolis on 17.01.2017.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            File file = new File("C:\\Users\\innopolis\\IdeaProjects\\DevelopmentPlan_JSP_SPRING\\src\\main\\webapp\\WEB-INF\\hibernate.cfg.xml");

            //return new AnnotationConfiguration().configure(file).buildSessionFactory();
            //Initial SessionFactory creation failed.java.lang.NoClassDefFoundError: org/hibernate/cfg/Mappings
            //http://stackoverflow.com/questions/35981363/getting-error-java-lang-noclassdeffounderror-org-hibernate-cfg-mappings-in-a

            return new Configuration().configure(file).buildSessionFactory();

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
