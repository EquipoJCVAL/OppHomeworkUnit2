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
    public Opportunity(Product product, int quantity, Contact decisionMaker, Status status, Account accountOpportunity, SalesRep salesRepOpportunity) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.accountOpportunity = accountOpportunity;
        this.salesRepOpportunity = salesRepOpportunity;
    }
    @Override
    public String toString() {
        return String.format(
                "Opportunity [Id=%d, Product='%s', DecisionMaker='%s', Status='%s', AccountId='%s', SalesRep='%s']",
                id, product, decisionMaker.getName(), status, accountOpportunity.getId(), salesRepOpportunity.getName());
    }

    public static void closeLost(Long id) throws ClassNotFoundException {
        boolean found = false;
        for (int i = 0; i < opportunitiesList.size(); i++) {
            if (opportunitiesList.get(i).getId() == id) {
                opportunitiesList.get(i).setStatus(Status.CLOSED_LOST);
                System.out.println("The opportunity status has been updated to lost!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("The ID provided does not correspond to any existing opportunities.");
        }
    }

    public static void closeWon(Long id){
        boolean found = false;
        for (int i = 0; i < opportunitiesList.size(); i++) {
            if (opportunitiesList.get(i).getId() == id) {
                opportunitiesList.get(i).setStatus(Status.CLOSED_WON);
                System.out.println("The opportunity status has been updated to won!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("The ID provided does not correspond to any existing opportunities.");
        }
    }


    public static void showOpportunities() {
        if (opportunitiesList.size() == 0) {
            System.out.println("Currently our systems don't have any Opportunities in the database");
        }
        for (int i = 0; i < opportunitiesList.size(); i++) {
            System.out.println("Opportunity with ID: " + opportunitiesList.get(i).getId() + "\n Product type: " + opportunitiesList.get(i).getProduct() + "\n Quantity of trucks: " + opportunitiesList.get(i).getQuantity());
            System.out.println("===");
        }
    }

    public static void lookUpOpportunity(Long id) {
        //we search the ID on the list of opportunities in the system, to check if we find it and we can print the information
        if (opportunitiesList.size() == 0){
            System.out.println("There are no Opportunities saved in our database.");
        } else {
            for (int i = 0; i < opportunitiesList.size(); i++) {
                Long leadID = opportunitiesList.get(i).getId();
                if (leadID.equals(id)) {
                    System.out.println(
                            "This ID corresponds to the opportunity created by " + opportunitiesList.get(i).getDecisionMaker().getName() + "\n" +
                                    "It's " + opportunitiesList.get(i).getQuantity() + "trucks of " + opportunitiesList.get(i).getProduct() + "products. \n" +
                                    "It's current status is; " + opportunitiesList.get(i).getStatus());
                } else {
                    System.out.println("The ID you introduced doesn't correspond with any Opportunities in our database.");
                }
            }
        }

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








