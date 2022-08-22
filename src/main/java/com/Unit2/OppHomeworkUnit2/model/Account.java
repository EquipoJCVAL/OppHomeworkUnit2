package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    @OneToMany (mappedBy = "accountContact")
    private List<Contact> contactList;
    @OneToMany (mappedBy = "accountOpportunity")
    private List<Opportunity> opportunityList;


    public static List<Account> accountsList = new ArrayList<>();

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


    public static void showAccounts() {
        //we check to see if the arraylist is empty, so we can display the proper message
        if (accountsList.size() == 0) {
            System.out.println("Currently our systems don't have any Accounts in the database");
        }
        //otherwise, we proceed to print out all of the accounts in the system.
        for (int i = 0; i < accountsList.size(); i++) {
            System.out.println("Account with ID: " + accountsList.get(i).getId() + "\n" +
                    accountsList.get(i).getIndustry() + " company based in: " + accountsList.get(i).getCity() + ", " +
                    accountsList.get(i).getCountry() + "\n" + accountsList.get(i).getEmployeeCount() + " employees.");
            System.out.println("===");
        }
    }

    public static void lookUpAccount(Long id){
        //we search the ID on the list of accounts in the system, to check if we find it and we can print the information
            for (int i = 0; i < accountsList.size(); i++) {
                Integer leadID = accountsList.get(i).getId();
                if (leadID.equals(id)) {

                    System.out.println(
                            "This ID corresponds to the account from a " + accountsList.get(i).getIndustry() +
                            " company with " + accountsList.get(i).getEmployeeCount() + "employees. \n" +
                            "It's based in " + accountsList.get(i).getCity() + ", " + accountsList.get(i).getCountry() + ".");
                } else {
                    System.out.println("The ID you introduced doesn't correspond with any Account in our database.");
                }
            }
        }

    //getters
    public int getId() {
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
}
