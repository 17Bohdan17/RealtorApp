package org.example.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.Entity.Agreement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgreementModel {
    private static final Logger logger = Logger.getLogger(AgreementModel.class.getName());
    @Getter
    private final ObservableList<AgreementViewModel> agreementViewModels = FXCollections.observableArrayList();
    private final SessionFactory sessionFactory;

    public AgreementModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List <Agreement> agreementList = session.createQuery("from Agreement ", Agreement.class).list();
            for (Agreement agreement : agreementList){
                agreementViewModels.add(convertToAgreementViewModel(agreement));
            }

            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching data from the database", e);

        }
    }

    private AgreementViewModel convertToAgreementViewModel (Agreement agreement){
        return new AgreementViewModel(agreement.getAgreementId(), agreement.getObjectId(), agreement.getClientId(),
             agreement.getAgreementPrice(), agreement.getAgreementDate(), agreement.getAgreementStatus());
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


}
