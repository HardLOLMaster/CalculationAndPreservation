/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.dao;

import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Класс доступа к объекту адресов в БД
 * @author HardLOLMaster
 */
public class AddressDAO implements DataAccess {

    @Override
    public Object getById(long id) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Address address = (Address) session.get(Address.class, id);
        session.close();
        return address;
    }

    @Override
    public void update(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Address address = (Address) t;
        Transaction tx = session.beginTransaction();
        session.update(address);
        tx.commit();
        session.close();
    }

    @Override
    public void save(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Address address = (Address) t;
        Transaction tx = session.beginTransaction();
        session.save(address);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Address address = (Address) t;
        Transaction tx = session.beginTransaction();
        session.delete(address);
        tx.commit();
        session.close();
    }

}
