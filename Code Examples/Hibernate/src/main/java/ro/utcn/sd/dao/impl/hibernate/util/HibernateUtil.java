package ro.utcn.sd.dao.impl.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final String                  CONFIG_FILE_NAME = "hibernate.cfg.xml";
    private static       SessionFactory          sessionFactory   = buildSessionFactory();
    private static       StandardServiceRegistry registry;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create registry
            registry = new StandardServiceRegistryBuilder().configure().build();
            // Create MetadataSources
            MetadataSources sources = new MetadataSources(registry);
            // Create Metadata
            Metadata metadata = sources.getMetadataBuilder().build();
            // Create SessionFactory
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
