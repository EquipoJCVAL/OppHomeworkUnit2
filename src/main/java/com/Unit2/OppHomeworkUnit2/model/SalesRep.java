package com.Unit2.OppHomeworkUnit2.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany (mappedBy = "salesRepLead")
    List<Lead> leadList;

    @OneToMany (mappedBy = "salesRepOpportunity")
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

