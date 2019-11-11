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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HardLOLMaster
 */
public class ChangePhServlet extends HttpServlet {

    private final PolicyHolderService phService = new PolicyHolderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обновление объекта PolicyHolder в БД
        // Установка кодировки полей запрос в UTF-8
        req.setCharacterEncoding("UTF-8");
        PolicyHolder ph = phService.getById(Long.parseLong(req.getParameter("id")));
        // Объект форматирования строки в дату
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = formatter.parse(req.getParameter("birthday"));
        } catch (ParseException ex) {
            Logger.getLogger(NewPh.class.getName()).log(Level.SEVERE, null, ex);
        }
        ph.setBirthday(birthday);
        ph.setSurname(req.getParameter("surname"));
        ph.setFirst_name(req.getParameter("first_name"));
        ph.setSecond_name(req.getParameter("second_name"));
        ph.setPassport_number(Integer.parseInt(req.getParameter("number")));
        ph.setPassport_series(Integer.parseInt(req.getParameter("series")));
        phService.update(ph);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Пересылка запроса в .jsp
        req.setCharacterEncoding("UTF-8");
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/changeForm.jsp");
        reqDis.forward(req, resp);
    }

}
