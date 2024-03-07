/*
 * HibernateUtil
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, який надає методи для роботи з Hibernate.
 *              Використовується для налаштування фабрики сесій та з'єднання з базою даних.
 */

package RealtorApp.util.hibernate;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    // Шлях до конфігураційного файлу Hibernate
    private final static String path = "src/main/resources/hibernate.cfg.xml";

    // Фабрика сесій Hibernate
    @Getter
    private static final SessionFactory sessionFactory = initSessionFactory();

    // Ініціалізація фабрики сесій Hibernate
    private static SessionFactory initSessionFactory() {
        // Завантаження конфігурацій з файлу hibernate.cfg.xml
        Configuration configuration = new Configuration()
                .configure(new File(path));
        // Побудова фабрики сесій
        return configuration.buildSessionFactory();
    }

    // Метод для закриття фабрики сесій
    public static void close() {
        getSessionFactory().close();
    }
}
