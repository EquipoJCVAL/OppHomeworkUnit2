package com.Unit2.OppHomeworkUnit2.model;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany (mappedBy = "salesRepLead")
    List<Lead> leadList;

    @OneToMany(mappedBy = "salesRepOpportunity")
    List<Opportunity> opportunityList;

    //constructor
    public SalesRep() {}

    public SalesRep(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "SalesRep[Id=%d, Name='%s']",
                id, name);
    }

    //we use again the name regex to ensure the new sales rep has a valid name.
    static String nameRegex = "^[A-Z][a-z]*[ ][A-Z][a-z]+$";

    public static void newSalesRep() {

        //With this method we simply call the Scanner so that we can get the 4 parameters to create a new lead.
        //And then test if they are valid matching them with the existing Regex variables.
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input the new Sales Rep name");
        String leadName = sc.nextLine();
        while (!leadName.matches(nameRegex)) {
            System.out.println("The name introduced is not valid, please only use letters and capitalize the first one of each name.");
            leadName = sc.nextLine();
        }
    }

    //getters
    public String getName() {
        return name;
    }
    public Long getId() {return id;}

    public List<Lead> getLeadList() {return leadList;}

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setId(Long id) {this.id = id;}
    public void setLeadList(List<Lead> leadList) {
        this.leadList = leadList;
    }
    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }
}

