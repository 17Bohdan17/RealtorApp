package org.example.Hibernate;

import org.example.Entity.Object;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Object object = new Object();
        object.setStreet("Київська");
        object.setStreetNum(91);
        object.setRoomCount((short)4);
        object.setArea(45.7);
        object.setPrice(34000);
        object.setStatus("AVAILABLE");
        object.setMinBedrooms((short)1);
        object.setMinBathrooms((short)1);
        object.setGarage(true);
        object.setGarden(true);
        object.setPool(false);

        session.save(object);
        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();


    }
}