/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HardLOLMaster
 */
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "contract_number")
    private Integer contract_number;

    @Column(name = "conclusion_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date conclusion_date;
    
    @Column(name = "calculation_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date calculation_date;
    
    @ManyToOne
    @JoinColumn(name = "policyholder")
    private PolicyHolder policyholder;

    @OneToOne
    @JoinColumn(name = "property")
    private Address property;

    @Column(name = "prize")
    private Float prize;

    @Column(name = "sum")
    private int sum;

    @Column(name = "date_from")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTo;

    public Contract() {

    }

    public Date getCalculation_date() {
        return calculation_date;
    }

    public void setCalculation_date(Date calculation_date) {
        this.calculation_date = calculation_date;
    }

    public long getId() {
        return id;
    }

    public Date getConclusion_date() {
        return conclusion_date;
    }

    public void setConclusion_date(Date conclusion_date) {
        this.conclusion_date = conclusion_date;
    }

    public PolicyHolder getPolicyholder() {
        return policyholder;
    }

    public void setPolicyholder(PolicyHolder policyholder) {
        this.policyholder = policyholder;
    }

    public Address getProperty() {
        return property;
    }

    public void setProperty(Address property) {
        this.property = property;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getContract_number() {
        return contract_number;
    }

    public void setContract_number(Integer contract_number) {
        this.contract_number = contract_number;
    }
}
