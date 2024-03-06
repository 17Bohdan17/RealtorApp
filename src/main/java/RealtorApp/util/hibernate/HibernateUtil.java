package RealtorApp.util.hibernate;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private final static String path = "src/main/resources/hibernate.cfg.xml";

    @Getter
    private static final SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {

        Configuration configuration = new Configuration()
                .configure(new File(path));
        return configuration.buildSessionFactory();

    }

    public static void close() {
        getSessionFactory().close();
    }
}
