/*
 * ClientModel
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що представляє модель клієнта в програмі RealtorApp.
 *              Він відповідає за отримання даних з бази даних про клієнтів, конвертацію
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
import RealtorApp.model.entity.Client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientModel {
    // Логер для ведення журналу подій
    private static final Logger logger =
            Logger.getLogger(ClientModel.class.getName());

    // Список моделей представлення клієнтів
    @Getter
    private final ObservableList<ClientViewModel> clientViewModels =
            FXCollections.observableArrayList();

    // Фабрика сеансів Hibernate
    private final SessionFactory sessionFactory;

    // Конструктор класу ClientModel, який налаштовує фабрику сеансів та отримує дані з бази даних
    public ClientModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    // Метод для отримання даних з бази даних про клієнтів
    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Client> clientList =
                    session.createQuery("from Client ", Client.class).list();

            for (Client client : clientList) {
                clientViewModels.add(convertToClientViewModel(client));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);
        }
    }

    // Метод для конвертації об'єкту клієнта в модель представлення клієнта
    private ClientViewModel convertToClientViewModel(Client client) {
        return new ClientViewModel(
                client.getClientId(),
                client.getFirstName(),
                client.getSecondName(),
                client.getContactNum(),
                client.getReqId(),
                client.getClient());
    }
}
