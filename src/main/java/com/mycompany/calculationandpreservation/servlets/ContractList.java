/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.servlets;

import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.services.ContractService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HardLOLMaster
 */
public class ContractList extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    private ContractService contractService = new ContractService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Вывод на главный экран списка всех контрактов
        System.out.println("GENIUS");
        List<Contract> contracts = contractService.getAll();
        req.setAttribute("contracts", contracts);
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/contracts.jsp");
        reqDis.forward(req, resp);
    }
}
