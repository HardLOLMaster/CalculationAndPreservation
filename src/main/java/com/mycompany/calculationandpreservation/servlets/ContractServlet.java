/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.servlets;

import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.services.ContractService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HardLOLMaster
 */
public class ContractServlet extends HttpServlet {

    private ContractService contractService = new ContractService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Открытие контрактов для просмотра
        Long id = (long) -1;
        if (req.getParameter("id")!=null){
            id = Long.parseLong(req.getParameter("id"));
        }
        Contract c = contractService.getById(id);
        req.setAttribute("contract", c);
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/openContract.jsp");
        reqDis.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Contract c = contractService.checkRequest(req);
        req.setAttribute("contract", c);
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/openContract.jsp");
        reqDis.forward(req, resp);
    }
}
