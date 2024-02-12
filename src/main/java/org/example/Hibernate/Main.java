package org.example.Hibernate;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Main extends Application {
    public static void main(String[] args) {

        Application.launch();

        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }
}