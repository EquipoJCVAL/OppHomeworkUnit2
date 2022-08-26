package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Product;
import com.Unit2.OppHomeworkUnit2.model.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Product product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "decision_maker_id")
    private Contact decisionMaker;
    @ManyToOne
    @JsonIgnore
    private Account accountOpportunity;
    @ManyToOne
    private SalesRep salesRepOpportunity;
    @Enumerated(value = EnumType.STRING)
    private Status status;


    public static List<Opportunity> opportunitiesList = new ArrayList<>();


    //constructor
    public Opportunity(){
    }
    public Opportunity(Product product, int quantity, Contact decisionMaker, Status status) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
    }
    @Override
    public String toString() {
        return String.format(
                "Opportunity [Id=%d, Product='%s', DecisionMaker='%s', Status='%s', AccountId='%s', SalesRep='%s']",
                id, product, decisionMaker.getName(), status, accountOpportunity.getId(), salesRepOpportunity.getName());
    }



    //getters
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public SalesRep getSalesRepOpportunity() {
        return salesRepOpportunity;
    }

    public Account getAccountOpportunity() {
        return accountOpportunity;
    }

    public static List<Opportunity> getOpportunitiesList() {
        return opportunitiesList;
    }



    //setters

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAccountOpportunity(Account accountOpportunity) {
        this.accountOpportunity = accountOpportunity;
    }

    public void setSalesRepOpportunity(SalesRep salesRepOpportunity) {
        this.salesRepOpportunity = salesRepOpportunity;
    }

    public static void setOpportunitiesList(List<Opportunity> opportunitiesList) {
        Opportunity.opportunitiesList = opportunitiesList;
    }

    public void setId(Long id) {
        this.id = id;
    }
}








