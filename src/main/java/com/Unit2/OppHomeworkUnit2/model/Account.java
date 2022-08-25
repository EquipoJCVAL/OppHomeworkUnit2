package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany (mappedBy = "accountContact")

    private List<Contact> contactList;
    @OneToMany (mappedBy = "accountOpportunity")

    private List<Opportunity> opportunityList;

    public Account() {

    }

    //constructor
    public Account(Industry industry, int employeeCount, String city, String country, List<Contact> contactList, List<Opportunity> opportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }


    @Override
    public String toString() {
        return String.format(
                "Account [Id=%d, Industry='%s', EmployeeCount='%d', City='%s', Country='%s']",
                id, industry, employeeCount, city, country);
    }


    //getters
    public Long getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    //setters


    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
