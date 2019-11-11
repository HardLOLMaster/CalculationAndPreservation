/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.servlets;

import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.services.PolicyHolderService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HardLOLMaster
 */
@WebServlet(name = "NewPh", urlPatterns = {"/newPh"})
public class NewPh extends HttpServlet {

    private PolicyHolderService phService = new PolicyHolderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PolicyHolder ph = new PolicyHolder();
        ph.setSurname(req.getParameter("surname"));
        ph.setFirst_name(req.getParameter("first_name"));
        ph.setSecond_name(req.getParameter("second_name"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse(req.getParameter("birthday"));
        } catch (ParseException ex) {
            Logger.getLogger(NewPh.class.getName()).log(Level.SEVERE, null, ex);
        }
        ph.setBirthday(birthday);
        phService.save(ph);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/newPh.jsp");
        reqDis.forward(req, resp);
    }

}
