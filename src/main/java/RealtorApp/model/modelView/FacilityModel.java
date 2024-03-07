/*
 * FacilityModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель зручностей в програмі RealtorApp.
 *              Відповідає за отримання даних з бази даних про зручності,
 *              конвертацію їх у відповідні об'єкти моделі та збереження у
 *              колекції для подальшого використання у програмі.
 */

package RealtorApp.model.modelView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import RealtorApp.model.entity.Facilities;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class FacilityModel {

    // Логер для ведення журналу подій
    private static final Logger logger = Logger.getLogger(FacilityModel.class.getName());

    // Список моделей представлення умовностей
    private final ObservableList<FacilityViewModel> facilityViewModels = FXCollections.observableArrayList();

    // Фабрика сеансів Hibernate
    private final SessionFactory sessionFactory;

    // Конструктор класу FacilityModel, який налаштовує фабрику сеансів та отримує дані з бази даних
    public FacilityModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних про умовності
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Facilities> facilitiesList = session.createQuery("from Facilities ", Facilities.class).list();

            for (Facilities facilities : facilitiesList) {
                facilityViewModels.add(convertToFacilityViewModel(facilities));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching data from the database", e);
        }
    }

    // Метод для конвертації об'єкту умовностей в модель представлення умовностей
    private FacilityViewModel convertToFacilityViewModel(Facilities facilities) {
        return new FacilityViewModel(
                facilities.getFacilityId(),
                facilities.getObjectReferenceId(),
                facilities.getMinBedrooms(),
                facilities.getMinBathrooms(),
                facilities.getGarage(),
                facilities.getGarden(),
                facilities.getPool(),
                facilities.getFacility());
    }
}
