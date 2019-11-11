/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HardLOLMaster
 */
@Entity
@Table(name = "policyholders")
public class PolicyHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "policyholder", orphanRemoval = true)
    private List<Contract> contracts;

    @Column(name = "surname")
    private String surname;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "second_name")
    private String second_name;
    @Column(name = "birthday")

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;

    @Column(name = "passport_series")
    private Integer passport_series;

    @Column(name = "passport_number")
    private Integer passport_number;

    public PolicyHolder() {

    }

    @Override
    public String toString() {
        return "PolicyHolder{" + "id=" + getId() 
                + ", contract=" + getContracts() 
                + ", surname=" + getSurname() 
                + ", first_name=" + getFirst_name() 
                + ", second_name=" + getSecond_name() 
                + ", birthday=" + getBirthday() 
                + ", passport_series=" + getPassport_series() 
                + ", passport_number=" + getPassport_number() + '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPassport_series() {
        return passport_series;
    }

    public void setPassport_series(Integer passport_series) {
        this.passport_series = passport_series;
    }

    public Integer getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(Integer passport_number) {
        this.passport_number = passport_number;
    }

    public Long getId() {
        return id;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContract(Contract contract) {
        this.contracts.add(contract);
    }

    public void setPassport_series(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
