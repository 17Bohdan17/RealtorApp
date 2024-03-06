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

public class FacilityModel {
    private static final Logger logger =
            Logger.getLogger(FacilityModel.class.getName());
    @Getter
    private final ObservableList<FacilityViewModel> facilityViewModels =
            FXCollections.observableArrayList();

    private final SessionFactory sessionFactory;

    public FacilityModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Facilities> facilitiesList =
                    session.createQuery("from Facilities ",
                            Facilities.class).list();

            for (Facilities facilities : facilitiesList) {
                facilityViewModels.add(convertToFacilityViewModel(facilities));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);
        }
    }

    private FacilityViewModel
    convertToFacilityViewModel(Facilities facilities) {
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

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
