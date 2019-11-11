/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.dao;

import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Класс доступа к объеку контрактов в БД
 * @author HardLOLMaster
 */
public class ContractDAO implements DataAccess {

    @Override
    public Contract getById(long id) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Contract contract = (Contract) session.get(Contract.class, id);
        session.close();
        return contract;
    }

    @Override
    public void update(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Contract contract = (Contract) t;
        Transaction tx = session.beginTransaction();
        session.update(contract);
        tx.commit();
        session.close();
    }

    @Override
    public void save(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Contract contract = (Contract) t;
        Transaction tx = session.beginTransaction();
        session.save(contract);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Object t) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Contract contract = (Contract) t;
        Transaction tx = session.beginTransaction();
        session.delete(contract);
        tx.commit();
        session.close();
    }

    public List<Contract> getAll() {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<Contract> contracts = session.createQuery("FROM Contract as c").list();
        session.close();
        return contracts;
    }

    public Contract getContractByContractNumber(int cn) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<Contract> contract = session.createQuery(
                "FROM Contract as c WHERE contract_number='" + cn + '\'')
                .list();
        session.close();
        if (0 == contract.size()) {
            return null;
        } else {
            return contract.get(0);
        }
    }

    public PolicyHolder getPolicyHolderById(int id) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        PolicyHolder policyHolder = (PolicyHolder) session.createQuery(
                "FROM policygolders WHERE id='" + id + '\'');
        session.close();
        return policyHolder;
    }

    public Address getAddressById(int id) {
        Session session = SessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Address address = (Address) session.createQuery(
                "FROM address WHERE id=" + id);
        session.close();
        return address;
    }
}
