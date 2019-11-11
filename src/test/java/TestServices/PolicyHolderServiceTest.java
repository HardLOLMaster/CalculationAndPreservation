/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestServices;

import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;
import com.mycompany.calculationandpreservation.services.PolicyHolderService;
import com.mycompany.calculationandpreservation.servlets.NewPh;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HardLOLMaster
 */
public class PolicyHolderServiceTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PolicyHolderService phService = new PolicyHolderService();
        PolicyHolder ph = null;
        /*try {
            ph = phService.getById(1);
        } catch (Exception e) {
            System.err.println("-------------------------------\n"
                    + "Ошибка = " + e);
        }
        System.out.println("Policy Holder ID = " + ph.getId());
        List<Contract> contracts = ph.getContracts();
        System.out.println("Policy Holder Contract 0 ID = " + contracts.get(0).getId());
        //List<PolicyHolder> phs = phService.getByFio("Иванов", "Иван", "Иванович");
        //System.out.println("Policy Holder 0 ID = " + phs.get(0).getId());*/
        PolicyHolder newPh = new PolicyHolder();
        newPh.setSurname("1");
        newPh.setFirst_name("2");
        newPh.setSecond_name("3");
        newPh.setPassport_series(null);
        newPh.setPassport_number(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse("2019-04-05");
        } catch (ParseException ex) {
            Logger.getLogger(NewPh.class.getName()).log(Level.SEVERE, null, ex);
        }
        newPh.setBirthday(birthday);
        phService.save(newPh);
        System.out.println(newPh.getId());
        SessionFactoryUtil.Close();

    }

}
