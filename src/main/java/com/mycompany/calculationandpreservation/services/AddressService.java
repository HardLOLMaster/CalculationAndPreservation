/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.services;

import com.mycompany.calculationandpreservation.database.dao.AddressDAO;
import com.mycompany.calculationandpreservation.database.entities.Address;

/**
 * Класс реализующий бизнес логику объекта Address
 *
 * @author HardLOLMaster
 */
public class AddressService {

    private AddressDAO addressDAO = new AddressDAO();

    public void save(Address a) {
        addressDAO.save(a);
    }

    public void update(Address a) {
        addressDAO.update(a);
    }

    public void delete(Address a) {
        addressDAO.delete(a);
    }

    public Address getById(Long id) {
        return (Address) addressDAO.getById(id);
    }
}
