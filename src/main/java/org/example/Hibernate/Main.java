package org.example.Hibernate;

import org.hibernate.Session;

public class Main  {
    public static void main(String[] args) {
        MainWindow controller = new MainWindow();
        controller.run();


        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}