package org.example.Hibernate;

import org.example.Entity.Object;
import org.hibernate.Session;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Object object = new Object();
        object.setStreet("Шевченка");
        object.setStreetNum(91);
        object.setRoomCount(4);
        object.setArea(45.7);
        object.setPrice(new BigDecimal("47000"));
        object.setStatus("AVAILABLE");
        object.setMinBedrooms((short)1);
        object.setMinBathrooms((short)1);
        object.setGarage(true);
        object.setGarden(true);
        object.setPool(false);

        session.persist(object);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();


    }
}