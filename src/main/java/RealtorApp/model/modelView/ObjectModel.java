/*
 * ObjectModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель об'єктів нерухомості в програмі RealtorApp.
 *              Відповідає за отримання даних з бази даних про об'єкти,
 *              конвертацію їх у відповідні об'єкти моделі та збереження
 *              у колекції для подальшого використання у програмі.
 */

package RealtorApp.model.modelView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import RealtorApp.model.entity.Object;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class ObjectModel {

    // Логер для ведення журналу подій
    private static final Logger logger = Logger.getLogger(ObjectModel.class.getName());

    // Список моделей представлення об'єктів
    private final ObservableList<ObjectViewModel> objectViewModels = FXCollections.observableArrayList();

    // Фабрика сеансів Hibernate
    private final SessionFactory sessionFactory;

    // Конструктор класу ObjectModel, який налаштовує фабрику сеансів та отримує дані з бази даних
    public ObjectModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних про об'єкти
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Object> objectList = session.createQuery("from Object", Object.class).list();

            for (Object object : objectList) {
                objectViewModels.add(convertToObjectViewModel(object));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching data from the database", e);
        }
    }

    // Метод для конвертації об'єкту об'єкта в модель представлення об'єкта
    private ObjectViewModel convertToObjectViewModel(Object object) {
        return new ObjectViewModel(
                object.getObjectId(),
                object.getStreet(),
                object.getStreetNum(),
                object.getArea(),
                object.getPrice(),
                object.getStatus(),
                object.getRoomCount(),
                object.getObject());
    }
}
