package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    @OneToMany(mappedBy = "accountContact")

    private List<Contact> contactList;
    @OneToMany(mappedBy = "accountOpportunity")

    private List<Opportunity> opportunityList;

    public Account() {

    }

    //constructor
    public Account(Industry industry, int employeeCount, String city, String country) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;

    }


    @Override
    public String toString() {
        return String.format(
                "Account [Id=%d, Industry='%s', EmployeeCount='%d', City='%s', Country='%s']",
                id, industry, employeeCount, city, country);
    }

    public static Account createAccount() {

        Scanner sc = new Scanner(System.in);
        String wordRegex = "([A-Z][a-z]+([ ]?[a-z]?['-]?)*)+";
        String numRegex = "[^a-z ]*([.0-9])*\\d";

        System.out.println("Opportunity successfully created! To complete the process you must create an Account.");
        System.out.println("City name: ");
        String city = sc.nextLine();

        while (!city.matches(wordRegex)) {
            System.out.println("Please, insert a valid city name capitalized (for example 'New York'): ");
            city = sc.nextLine();
        }

        System.out.println("Country of the organization: ");
        String country = sc.nextLine();

        while (!country.matches(wordRegex)) {
            System.out.println("Please, insert a valid country name with the first letter capitalized: ");
            country = sc.nextLine();
        }

        System.out.println("Number of employees: ");
        String employeeStr = sc.nextLine();

        while (!employeeStr.matches(wordRegex)) {
            System.out.println("The value introduced is not a valid number, insert a valid value");
            employeeStr = sc.nextLine();

        }
        int employees = Integer.parseInt(employeeStr);

        System.out.println("Select the product (insert the number)\n1 - ECOMMERCE\n2 - MANUFACTURING\n3 - MEDICAL\n4 - PRODUCE\n5 - OTHER");
        String chosenTwo = sc.nextLine();

        Industry industry = null;
        while (industry == null) {
            switch (chosenTwo) {
                case "1" -> industry = Industry.ECOMMERCE;
                case "2" -> industry = Industry.MANUFACTURING;
                case "3" -> industry = Industry.MEDICAL;
                case "4" -> industry = Industry.PRODUCE;
                case "5" -> industry = Industry.OTHER;
                default -> {
                    System.out.println("Invalid number, try again.");
                    chosenTwo = sc.nextLine();
                }
            }
        }

        //Creates a new Account and a list for Contact and Opportunity
        Account account = new Account(industry, employees, city, country, );

        System.out.println("Account Created!\n");

        return account;
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
