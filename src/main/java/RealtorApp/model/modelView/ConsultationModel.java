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

public class ConsultationModel {
    private static final Logger logger =
            Logger.getLogger(ConsultationModel.class.getName());
    @Getter
    private final ObservableList<ConsultationViewModel> consultationViewModels
            = FXCollections.observableArrayList();

    private final SessionFactory sessionFactory;

    public ConsultationModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

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

    private ConsultationViewModel
    convertToConsultationViewModel(Consultation consultation) {
        return new ConsultationViewModel(
                consultation.getConsId(),
                consultation.getClientId(),
                consultation.getConsDate(),
                consultation.getConsStatus(),
                consultation.getConsultation());
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
