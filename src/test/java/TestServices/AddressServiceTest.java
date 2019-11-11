/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestServices;

import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.services.AddressService;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;

/**
 *
 * @author HardLOLMaster
 */
public class AddressServiceTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        Address address = null;
        try {
            address = addressService.getById((long)1);
        } catch (Exception e) {
            System.err.println("-------------------------------\n"
                    + "Ошибка = " + e);
        }
        try {
            Contract contract = address.getContract();
            System.out.println("Address Country = " + address.getCountry());
            System.out.println("Address Contract ID = " + contract.getId());
        } catch (Exception e) {
            System.err.println("-------------------------------\n"
                    + "Ошибка = " + e);
        }
        SessionFactoryUtil.Close();
    }

}
