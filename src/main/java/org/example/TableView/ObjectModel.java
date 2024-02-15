package org.example.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.example.Entity.Object;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectModel {
    private static final Logger logger = Logger.getLogger(ObjectModel.class.getName());
    @Getter
    private final ObservableList<ObjectViewModel> objectViewModels = FXCollections.observableArrayList();
    private final SessionFactory sessionFactory;

    public ObjectModel() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List <Object> objectList = session.createQuery("from Object", Object.class).list();
            for (Object object : objectList){
                objectViewModels.add(convertToObjectViewModel(object));
            }




            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while fetching data from the database", e);

        }
    }

    private ObjectViewModel convertToObjectViewModel (Object object){
        return new ObjectViewModel(object.getObjectId(), object.getStreet(), object.getStreetNum(), object.getArea(),
                object.getPrice(), object.getStatus(), object.getRoomCount());
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


}
