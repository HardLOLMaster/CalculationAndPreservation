/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.servlets;

import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.services.PolicyHolderService;
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
public class PolicyHolderServlet extends HttpServlet {

    private PolicyHolderService phService = new PolicyHolderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String surname = req.getParameter("surname");
        String first_name = req.getParameter("first_name");
        String second_name = req.getParameter("second_name");
        List<PolicyHolder> phList = null;
        if (second_name.equals("")) {
            phList = phService.getByFi(surname, first_name);
        } else {
            phList = phService.getByFio(surname, first_name, second_name);
        }
        req.setAttribute("policyholders", phList);
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/choosePhForm.jsp");
        reqDis.forward(req, resp);
    }

}
