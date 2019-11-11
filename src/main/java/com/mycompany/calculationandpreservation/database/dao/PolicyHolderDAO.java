/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.dao;

import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Класс доступа к объекту страхователей в БД
 * @author HardLOLMaster
 */
public class PolicyHolderDAO implements DataAccess {

    @Override
    public Object getById(long id) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PolicyHolder policyHolder = (PolicyHolder) session.get(PolicyHolder.class, id);
        session.close();
        return policyHolder;
    }

    @Override
    public void update(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PolicyHolder policyHolder = (PolicyHolder) t;
        Transaction tx = session.beginTransaction();
        session.update(policyHolder);
        tx.commit();
        session.close();
    }

    @Override
    public void save(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PolicyHolder policyHolder = (PolicyHolder) t;
        Transaction tx = session.beginTransaction();
        session.save(policyHolder);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PolicyHolder policyHolder = (PolicyHolder) t;
        Transaction tx = session.beginTransaction();
        session.delete(policyHolder);
        tx.commit();
        session.close();
    }

    public List<PolicyHolder> getByFio(String surname, String first_name, String second_name) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<PolicyHolder> PolicyHolders = session.createQuery(
                "FROM PolicyHolder as ph WHERE ph.surname='" + surname
                + "'and ph.first_name='" + first_name
                + "'and ph.second_name='" + second_name+'\'')
                .list();
        session.close();
        return PolicyHolders;
    }
    public List<PolicyHolder> getByFi(String surname, String first_name) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<PolicyHolder> PolicyHolders = session.createQuery(
                "FROM PolicyHolder as ph WHERE ph.surname='" + surname
                + "'and ph.first_name='" + first_name+'\'')
                .list();
        session.close();
        return PolicyHolders;
    }
}
