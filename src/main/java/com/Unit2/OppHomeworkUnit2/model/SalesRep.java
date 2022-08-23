package com.Unit2.OppHomeworkUnit2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;

    static List<SalesRep> salesRepList = new ArrayList<>();

    //constructor
    public SalesRep() {

    }
    public SalesRep(String name) {
        this.name = name;
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

    public static void showSalesRep() {
        //we check to see if the arraylist is empty, so we can display the proper message
        if (salesRepList.size() == 0) {
            System.out.println("Currently our systems don't have any Sales Rep in the database");
        }
        //otherwise, we proceed to print out all of the SalesRep in the system.
        else {
            for (int i = 0; i < salesRepList.size(); i++) {
                System.out.println("Lead with ID: " + salesRepList.get(i).getId() + " \n Name: " + salesRepList.get(i).getName());
                System.out.println("===");
            }
        }
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
}

