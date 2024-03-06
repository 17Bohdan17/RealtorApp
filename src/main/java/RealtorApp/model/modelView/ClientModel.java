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
    private static final Logger logger =
            Logger.getLogger(ClientModel.class.getName());

    @Getter
    private final ObservableList<ClientViewModel> clientViewModels =
            FXCollections.observableArrayList();

    private final SessionFactory sessionFactory;

    public ClientModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

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

    private ClientViewModel convertToClientViewModel(Client client) {
        return new ClientViewModel(
                client.getClientId(),
                client.getFirstName(),
                client.getSecondName(),
                client.getContactNum(),
                client.getReqId(),
                client.getClient());
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
