/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.services;

import com.mycompany.calculationandpreservation.database.dao.PolicyHolderDAO;
import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import java.util.List;

/**
 * Класс реализующий бизнес логику объекта PolicyHolder
 *
 * @author HardLOLMaster
 */
public class PolicyHolderService {

    private PolicyHolderDAO phDAO = new PolicyHolderDAO();

    public PolicyHolder getById(long id) {
        return (PolicyHolder) phDAO.getById(id);
    }

    public void update(PolicyHolder ph) {
        phDAO.update(ph);
    }

    public void save(PolicyHolder ph) {
        phDAO.save(ph);
    }

    public void delete(PolicyHolder ph) {
        phDAO.delete(ph);
    }

    public List<PolicyHolder> getByFio(String surname, String first_name, String second_name) {
        return phDAO.getByFio(surname, first_name, second_name);
    }

    public List<PolicyHolder> getByFi(String surname, String first_name) {
        return phDAO.getByFi(surname, first_name);
    }
}
