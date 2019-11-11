/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculationandpreservation.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HardLOLMaster
 */
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    // index / индекс
    @Column(name = "ind")
    private String ind;

    @Column(name = "country")
    private String country;

    // район
    @Column(name = "region")
    private String region;

    // республика/край/область
    @Column(name = "country_state")
    private String country_state;

    // город
    @Column(name = "city")
    private String city;

    // улица
    @Column(name = "street")
    private String street;

    // дом
    @Column(name = "building")
    private Integer building;

    // корпус
    @Column(name = "corpus")
    private String corpus;

    // строение
    @Column(name = "building_structure")
    private String building_structure;

    // квартира
    @Column(name = "apartament")
    private Integer apartament;

    @Column(name = "building_area")
    private Float buildingArea;

    @Column(name = "building_year")
    private Integer buildingYear;

    @Column(name = "building_type")
    private String buildingType;

    public Address() {

    }

    public long getId() {
        return id;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Float buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getCountry_state() {
        return country_state;
    }

    public void setCountry_state(String country_state) {
        this.country_state = country_state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    public String getBuilding_structure() {
        return building_structure;
    }

    public void setBuilding_structure(String building_structure) {
        this.building_structure = building_structure;
    }

    public Integer getApartament() {
        return apartament;
    }

    public void setApartament(Integer apartament) {
        this.apartament = apartament;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(Integer buildingYear) {
        this.buildingYear = buildingYear;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
}
