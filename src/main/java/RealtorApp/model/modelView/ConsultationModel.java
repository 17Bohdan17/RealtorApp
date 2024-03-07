/*
 * ConsultationModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель консультації в програмі RealtorApp.
 *              Відповідає за отримання даних з бази даних про консультації,
 *              конвертацію їх у відповідні об'єкти моделі та збереження у колекції
 *              для подальшого використання у програмі.
 */

package RealtorApp.model.modelView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import RealtorApp.model.entity.Consultation;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class ConsultationModel {

    // Логер для ведення журналу подій
    private static final Logger logger =
            Logger.getLogger(ConsultationModel.class.getName());

    // Список моделей представлення консультацій
    private final ObservableList<ConsultationViewModel> consultationViewModels =
            FXCollections.observableArrayList();

    // Фабрика сеансів Hibernate
    private final SessionFactory sessionFactory;

    // Конструктор класу ConsultationModel, який налаштовує фабрику сеансів та отримує дані з бази даних
    public ConsultationModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних про консультації
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Consultation> consultationList =
                    session.createQuery("from Consultation ",
                            Consultation.class).list();

            for (Consultation consultation : consultationList) {
                consultationViewModels
                        .add(convertToConsultationViewModel(consultation));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);
        }
    }

    // Метод для конвертації об'єкту консультації в модель представлення консультації
    private ConsultationViewModel
    convertToConsultationViewModel(Consultation consultation) {
        return new ConsultationViewModel(
                consultation.getConsId(),
                consultation.getClientId(),
                consultation.getConsDate(),
                consultation.getConsStatus(),
                consultation.getConsultation());
    }
}
