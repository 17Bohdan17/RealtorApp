package org.example.Hibernate;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            // Загрузка конфигурационного файла hibernate.cfg.xml из ресурсов
            Configuration configuration = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"));
            return configuration.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed! " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void close() {
        getSessionFactory().close();
    }
}
