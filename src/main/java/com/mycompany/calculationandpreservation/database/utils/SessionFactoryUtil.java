/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.utils;

import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author HardLOLMaster
 */
public class SessionFactoryUtil {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration().configure();
                conf.addAnnotatedClass(Address.class);
                conf.addAnnotatedClass(PolicyHolder.class);
                conf.addAnnotatedClass(Contract.class);
                /*conf.setSessionFactoryObserver(new SessionFactoryObserver() {
                    @Override
                    public void sessionFactoryCreated(SessionFactory sf) {
                    }

                    @Override
                    public void sessionFactoryClosed(SessionFactory sf) {
                        StandardServiceRegistryBuilder.destroy(serviceRegistry);
                    }
                });*/

                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.err.println("-------------------------------\n"
                        + "SessionFactory error " + e);
            }
        }

        return sessionFactory;

    }

    public static void Close() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
