/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestServices;

import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.services.ContractService;
import com.mycompany.calculationandpreservation.database.utils.SessionFactoryUtil;
import java.util.List;

/**
 *
 * @author HardLOLMaster
 */
public class ContractServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ContractService contractService = new ContractService();
        Contract contract = null;
        /*try {
            contract = contractService.getById((long)1);
        } catch (Exception e) {
            System.err.println("-------------------------------\n"
                    + "Ошибка = " + e);
        }
        System.out.println("Contract ID = " + contract.getId());
        System.out.println("Property ID = " + contract.getProperty().getId());
        System.out.println("Policy Holder ID = " + contract.getPolicyholder().getId());

        /*System.out.println("\nContract SUM before update = " + contract.getSum());
        contract.setSum(150);
        contractService.update(contract);
        Contract contract2 = contractService.getById(1);
        System.out.println("Contract SUM after update = " + contract2.getSum());*/
        List<Contract> contracts = contractService.getAll();
        System.out.println("Contracts="+(contracts==null));
        System.out.println("Contracts Date From To:");
        for (Contract c : contracts) {
            System.out.println("\t Contract ID = "+c.getId());
        }
        /*Thread.sleep(3000);
        Contract c = contractService.getContractByContractNumber(6);
        System.out.println("\nContract Id = " + contract.getId());
        System.out.println("\nContract PolicyHolder Surname = "
                + contract.getPolicyholder().getSurname());
        */
        SessionFactoryUtil.Close();
    }

}
