/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.services;

import com.mycompany.calculationandpreservation.database.dao.ContractDAO;
import com.mycompany.calculationandpreservation.database.entities.Address;
import com.mycompany.calculationandpreservation.database.entities.Contract;
import com.mycompany.calculationandpreservation.database.entities.PolicyHolder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс реализующий бизнес логику объекта Contract
 *
 * @author HardLOLMaster
 */
public class ContractService {

    private ContractDAO contractDAO = new ContractDAO();

    public void save(Contract c) {
        contractDAO.save(c);
    }

    public void update(Contract c) {
        contractDAO.update(c);
    }

    public void delete(Contract c) {
        contractDAO.delete(c);
    }

    public Contract getById(Long id) {
        return contractDAO.getById(id);
    }

    public Contract getContractByContractNumber(int cn) {
        return contractDAO.getContractByContractNumber(cn);
    }

    public Contract checkRequest(HttpServletRequest req) {
        Contract contract = new Contract();
        Address address = new Address();
        PolicyHolder ph = new PolicyHolder();
        if ((!"".equals(req.getParameter("sum")))
                && (!"".equals(req.getParameter("dateFrom")))
                && (!"".equals(req.getParameter("dateTo")))
                && (!"".equals(req.getParameter("area")))) {
            Date dateFrom = null, dateTo = null, conclusion = null;
            LocalDate local = LocalDate.now();
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                dateFrom = formatter.parse(req.getParameter("dateFrom"));
                dateTo = formatter.parse(req.getParameter("dateTo"));
                conclusion = formatter.parse(local.toString());
            } catch (ParseException ex) {
            }
            int sum = Integer.parseInt(req.getParameter("sum"));
            String property = req.getParameter("property");
            Integer year = Integer.parseInt(req.getParameter("year"));
            Float area = Float.parseFloat(req.getParameter("area"));
            Float prize = -1.0f;
            if (dateCheck(dateFrom, dateTo, local)) {
                float kP = 0, kY = 0, kA = 0;
                switch (property) {
                    case "apartament":
                        kP = 1.7f;
                        break;
                    case "house":
                        kP = 1.5f;
                        break;
                    case "room":
                        kP = 1.3f;
                        break;
                }
                if (year < 2000) {
                    kY = 1.3f;
                } else if (year < 2014) {
                    kY = 1.6f;
                } else {
                    kY = 2f;
                }
                if (area < 50) {
                    kA = 1.2f;
                } else if (area < 100) {
                    kA = 1.5f;
                } else {
                    kA = 2f;
                }
                long days = (dateTo.getTime() - dateFrom.getTime()) / 1000 / 60 / 60 / 24;
                prize = (sum / days) * kY * kP * kA;
                String dateFromStr = (dateFrom.getYear() + 1900) + "-" + (dateFrom.getMonth() + 1) + "-";
                String dateToStr = (dateTo.getYear() + 1900) + "-" + (dateTo.getMonth() + 1) + "-";
                if (dateFrom.getDate() < 10) {
                    dateFromStr += "0" + dateFrom.getDate();
                } else {
                    dateFromStr += dateFrom.getDate();
                }
                if (dateTo.getDate() < 10) {
                    dateToStr += "0" + dateTo.getDate();
                } else {
                    dateToStr += dateTo.getDate();
                }
                if (prize < 0) {
                    prize = -prize;
                }
                /*req.setAttribute("prize", prize);
                req.setAttribute("dateFrom", dateFromStr);
                req.setAttribute("dateTo", dateToStr);
                req.setAttribute("conclusion_date", local);*/
            }
            /*req.setAttribute("sum", sum);
            req.setAttribute("property", property);
            req.setAttribute("year", year);
            req.setAttribute("area", area);*/
            address.setBuildingYear(year);
            address.setBuildingType(property);
            address.setBuildingArea(area);
            contract.setSum(sum);
            contract.setPrize(prize);
            contract.setDateFrom(dateFrom);
            contract.setDateTo(dateTo);
            contract.setConclusion_date(conclusion);
            //contract.setContract_number(conclusion);
            contract.setPolicyholder(ph);
            contract.setProperty(address);
        } else {
            req.setAttribute("fields", null);
            contract = null;
        }
        return contract;
    }

    private boolean dateCheck(Date dateFrom, Date dateTo, LocalDate local) {
        return (dateFromAndLocalDateCheck(dateFrom, local) && dateFromToCheck(dateFrom, dateTo));
    }

    private boolean dateFromAndLocalDateCheck(Date date, LocalDate local) {
        if ((date.getYear() + 1900) == local.getYear()) {
            if ((date.getMonth() + 1) == local.getMonthValue()) {
                if (date.getDate() >= local.getDayOfMonth()) {
                    return true;
                } else {
                    return false;
                }
            } else if ((date.getMonth() + 1) > local.getMonthValue()) {
                return true;
            } else {
                return false;
            }
        } else if ((date.getYear() + 1900) > local.getYear()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean dateFromToCheck(Date dateFrom, Date dateTo) {
        if (dateTo.getYear() == dateFrom.getYear()) {
            if (dateTo.getMonth() == dateFrom.getMonth()) {
                if (dateTo.getDate() > dateFrom.getDate()) {
                    return true;
                } else {
                    return false;
                }
            } else if (dateTo.getMonth() > dateFrom.getMonth()) {
                return true;
            } else {
                return false;
            }
        } else if (dateTo.getYear() > dateFrom.getYear()) {
            return true;
        } else {
            return false;
        }
    }

    public List<Contract> getAll() {
        return contractDAO.getAll();
    }
}
