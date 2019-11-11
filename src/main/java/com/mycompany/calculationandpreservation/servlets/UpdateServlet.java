/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.servlets;

import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import com.mycompany.calculationandpreservation.services.AddressService;
import com.mycompany.calculationandpreservation.services.ContractService;
import com.mycompany.calculationandpreservation.services.PolicyHolderService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HardLOLMaster
 */
public class UpdateServlet extends HttpServlet {

    private ContractService contractService = new ContractService();
    private AddressService addressService = new AddressService();
    private PolicyHolderService phService = new PolicyHolderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Contract c = contractService.checkRequest(req);
        req.setAttribute("contract", c);
        RequestDispatcher reqDis = req.getServletContext().getRequestDispatcher("/createContract.jsp");
        reqDis.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Contract contract = contractService.getById(Long.parseLong(req.getParameter("cId")));
        Address address = (Address) addressService.getById(Long.parseLong(req.getParameter("prId")));
        PolicyHolder ph = phService.getById(Long.parseLong(req.getParameter("phId")));
        Integer number = Integer.parseInt(req.getParameter("number"));
        Float prize = Float.parseFloat(req.getParameter("prize"));
        Date calcDate = null, dateFrom = null, dateTo = null, concDate = null;
        try {
            calcDate = formatter.parse(req.getParameter("date"));
            dateFrom = formatter.parse(req.getParameter("dateFrom"));
            dateTo = formatter.parse(req.getParameter("dateTo"));
            concDate = formatter.parse(req.getParameter("conclusion_date"));
        } catch (ParseException ex) {
            System.err.println("error parsing date in SaveContract");
        }
        Integer sum = Integer.parseInt(req.getParameter("sum"));
        contract.setSum(sum);
        contract.setCalculation_date(calcDate);
        contract.setConclusion_date(concDate);
        contract.setContract_number(number);
        contract.setDateFrom(dateFrom);
        contract.setDateTo(dateTo);
        contract.setPrize(prize);
        String property = req.getParameter("property");
        String country = req.getParameter("country");
        String country_state = req.getParameter("country_state");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        Integer year = Integer.parseInt(req.getParameter("year"));
        Integer apartament = Integer.parseInt(req.getParameter("apartament"));
        Float area = Float.parseFloat(req.getParameter("area"));

        Integer building = null;
        String index = null, building_structure = null, corpus = null, region = null;
        try {
            index = req.getParameter("index");
        } catch (Exception e) {
            System.err.println("error getting parameter in SaveContract");
        }
        try {
            building = Integer.parseInt(req.getParameter("building"));
        } catch (Exception e) {
            System.err.println("error getting parameter in SaveContract");
        }
        try {
            building_structure = req.getParameter("building_structure");
        } catch (Exception e) {
            System.err.println("error getting parameter in SaveContract");
        }
        try {
            corpus = req.getParameter("corpus");
        } catch (Exception e) {
            System.err.println("error getting parameter in SaveContract");
        }
        try {
            region = req.getParameter("region");
        } catch (Exception e) {
            System.err.println("error getting parameter in SaveContract");
        }

        address.setApartament(apartament);
        address.setBuilding(building);
        address.setBuildingArea(area);
        address.setBuildingType(property);
        address.setBuildingYear(year);
        address.setBuilding_structure(building_structure);
        address.setCity(city);
        address.setCorpus(corpus);
        address.setCountry(country);
        address.setCountry_state(country_state);
        address.setInd(index);
        address.setRegion(region);
        address.setStreet(street);
        addressService.update(address);
        contract.setProperty(address);
        /**
         * ****************************************
         */
        Integer pas_num = Integer.parseInt(req.getParameter("pas_number"));
        Integer pas_series = Integer.parseInt(req.getParameter("series"));
        ph.setPassport_number(pas_num);
        ph.setPassport_series(pas_series);
        contract.setPolicyholder(ph);
        contractService.update(contract);
        ph.setContract(contract);
        address.setContract(contract);
        phService.update(ph);
        resp.sendRedirect("/");
    }

}
