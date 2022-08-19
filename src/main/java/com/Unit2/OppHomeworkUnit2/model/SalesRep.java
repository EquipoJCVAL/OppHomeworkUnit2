package com.Unit2.OppHomeworkUnit2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalesRep {

    @Id
    String name;

    //constructor
    public SalesRep() {

    }

    public SalesRep(String name) {
        this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
}

