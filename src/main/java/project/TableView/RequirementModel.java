package project.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import project.Entity.Requirement;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequirementModel {
    private static final Logger logger =
            Logger.getLogger(RequirementModel.class.getName());

    @Getter
    private final ObservableList<RequirementViewModel> requirementViewModels =
            FXCollections.observableArrayList();

    private final SessionFactory sessionFactory;

    public RequirementModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<Requirement> requirementList =
                    session.createQuery("from Requirement ",
                            Requirement.class).list();

            for (Requirement requirement : requirementList) {
                requirementViewModels
                        .add(convertToRequirementViewModel(requirement));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "An error occurred while fetching data from the database",
                    e);

        }
    }

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
                requirement.getReqPool());
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
