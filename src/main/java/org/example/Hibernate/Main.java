package org.example.Hibernate;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        HibernateUtil.close();

        System.out.println("Hello World! ");


    }
}