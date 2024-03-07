/*
 * AgreementModel
 *
 * Version: 1.0
 * Author: Богдан Чирков
 *
 * Description: Клас, який представляє модель угоди в програмі RealtorApp.
 *              Він відповідає за отримання даних з бази даних про угоди, конвертацію
 *              їх у відповідні об'єкти моделі та збереження у колекції для подальшого
 *              використання у програмі.
 */

package RealtorApp.model.modelView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import RealtorApp.model.entity.Agreement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgreementModel {
    // Логер для ведення журналу подій
    private static final Logger logger =
            Logger.getLogger(AgreementModel.class.getName());

    // Список угод, який використовується для відображення угод в інтерфейсі
    @Getter
    private final ObservableList<AgreementViewModel> agreementViewModels =
            FXCollections.observableArrayList();

    // Фабрика сеансів Hibernate
    private final SessionFactory sessionFactory;

    // Конструктор класу AgreementModel, який налаштовує фабрику сеансів та отримує дані з бази даних
    public AgreementModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Agreement> agreementList =
                    session.createQuery("from Agreement ",
                            Agreement.class).list();

            for (Agreement agreement : agreementList) {
                agreementViewModels
                        .add(convertToAgreementViewModel(agreement));
            }

            transaction.commit();
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);

        }
    }

    // Метод для конвертації об'єкту угоди в модель угоди для відображення
    private AgreementViewModel
    convertToAgreementViewModel(Agreement agreement) {
        return new AgreementViewModel(
                agreement.getAgreementId(),
                agreement.getObjectId(),
                agreement.getClientId(),
                agreement.getAgreementPrice(),
                agreement.getAgreementDate(),
                agreement.getAgreementStatus(),
                agreement.getAgreement());
    }
}
