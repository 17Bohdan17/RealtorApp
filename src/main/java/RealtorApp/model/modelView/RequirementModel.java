/*
 * RequirementModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель вимог в програмі RealtorApp.
 *              Використовується для відображення даних про вимоги у таблиці або іншому візуальному елементі.
 */

package RealtorApp.model.modelView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import RealtorApp.model.entity.Requirement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class RequirementModel {
    // Логер для виведення повідомлень про помилки
    private static final Logger logger =
            Logger.getLogger(RequirementModel.class.getName());

    // Список для зберігання вимог
    private final ObservableList<RequirementViewModel> requirementViewModels =
            FXCollections.observableArrayList();

    // Фабрика сесій для взаємодії з базою даних
    private final SessionFactory sessionFactory;

    // Конструктор класу RequirementModel
    public RequirementModel() {
        // Ініціалізація фабрики сесій
        sessionFactory = new Configuration().configure().buildSessionFactory();
        // Отримання даних з бази даних
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            // Відкриття транзакції
            Transaction transaction = session.beginTransaction();

            // Отримання списку вимог з бази даних
            List<Requirement> requirementList =
                    session.createQuery("from Requirement",
                            Requirement.class).list();

            // Додавання кожної вимоги у список вимог моделі
            for (Requirement requirement : requirementList) {
                requirementViewModels.add(convertToRequirementViewModel(requirement));
            }

            // Завершення транзакції
            transaction.commit();
        } catch (Exception e) {
            // Логування помилок
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);
        }
    }

    // Метод для перетворення об'єкта вимоги на вимогу моделі
    private RequirementViewModel
    convertToRequirementViewModel(Requirement requirement) {
        return new RequirementViewModel(
                requirement.getReqId(),
                requirement.getReqMinBedrooms(),
                requirement.getReqMinBathrooms(),
                requirement.getReqMinimalArea(),
                requirement.getReqMaxPrice(),
                requirement.getReqStreet(),
                requirement.getReqGarage(),
                requirement.getReqGarden(),
                requirement.getReqPool(),
                requirement.getRequirement());
    }
}
