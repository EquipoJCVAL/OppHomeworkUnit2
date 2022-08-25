package com.Unit2.OppHomeworkUnit2.model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String companyName;
    @ManyToOne
    private Account accountContact;

    //constructors
    public Contact(){
    }

    public Contact(String name, String phoneNumber, String email, String companyName, Account accountContact) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.accountContact = accountContact;

    }

    //getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Long getId(){return id;}

    public Account getAccountContact() {
        return accountContact;
    }


    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setId(Long id) {this.id = id;}

    public void setAccountContact(Account accountContact) {
        this.accountContact = accountContact;
    }
}


