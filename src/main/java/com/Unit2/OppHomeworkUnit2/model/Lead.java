package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.Unit2.OppHomeworkUnit2.model.Enums.Product;
import com.Unit2.OppHomeworkUnit2.model.Enums.Status;
import com.Unit2.OppHomeworkUnit2.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
@Table (name = "lead_")
public class Lead {

    @Autowired
    LeadRepository leadRepository;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String companyName;
    @ManyToOne
    private SalesRep salesRepLead;


    //constructor
    public Lead() {
    }
    public Lead(String name, String phoneNumber, String email, String companyName, SalesRep salesRepLead) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRepLead = salesRepLead;
    }

    //We create the different Regex in variables so they can be easily called/accessed by the different methods without having to write them every single time.
    static String nameRegex = "^[A-Z][a-z]*[ ][A-Z][a-z]+$";
    static String phoneRegex = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$";
    static String emailRegex = "([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}";

    //We create a leadList so that they are all in the same list so that they can be accessed by show Leads
    static ArrayList<Lead> leadList = new ArrayList<Lead>();
    //We create an "oldLeadList" so that once the leads are converted and removed from the system, users should still be able to search that ID
    //and recieve that the lead with that ID is no longer in the system, aswell as a separate message if that ID doesn't exist/was never in the system.
    static ArrayList<Lead> oldLeadList = new ArrayList<Lead>();




    public static Lead newLead() {


        //LeadRepository leadRepository;

        //With this method we simply call the Scanner so that we can get the 4 parameters to create a new lead.
        //And then test if they are valid matching them with the existing Regex variables.
        Scanner sc = new Scanner(System.in);

                System.out.println("Please input the new Lead's name");
                String leadName = sc.nextLine();
                while(!leadName.matches(nameRegex)) {
                    System.out.println("The name introduced is not valid, please only use letters and capitalize the first one of each name.");
                    leadName = sc.nextLine();
                }

                System.out.println("Please input the new Lead's phone number");
                String leadPhone = sc.nextLine();
                while(!leadPhone.matches(phoneRegex)) {
                System.out.println("The phone number introduced is not valid, please only use numbers");
                    leadPhone = sc.nextLine();
                }

                System.out.println("Please input the new Lead's email");
                String leadEmail = sc.nextLine();
                while (!leadEmail.matches(emailRegex)) {
                    System.out.println("The email address introduced is not valid, please use the proper format");
                    leadEmail = sc.nextLine();
                }

                System.out.println("Please input the new Lead's company name");
                String leadCompany = sc.nextLine();

                SalesRep salesrep = new SalesRep();
                //once all the parameters are valid, we simply create the Lead.
                Lead newLead = new Lead(leadName, leadPhone, leadEmail, leadCompany, salesrep);

                return newLead;

    }


    public static Opportunity convertID(Lead lead) {
        String wordRegex = "([A-Z][a-z]+([ ]?[a-z]?['-]?)*)+";
        String numRegex = "[^a-z ]*([.0-9])*\\d";

        Scanner input = new Scanner(System.in);
        // Collect Opportunity parameters - number of trucks and product
        System.out.println("Fill in the following fields.");
        System.out.println("Number of trucks: ");
        String truckString = input.nextLine();

        if (!truckString.matches(numRegex)) {
            System.out.println("The introduced value is not valid, please introduce a valid value.");
            truckString = input.nextLine();
        }
        int truckNum = Integer.parseInt(truckString);

        System.out.println("Select the product (insert the number)\n1 - BOX\n2 - FLATBED\n3 - HYBRID");
        String chosenOneStr = input.nextLine();
        if (!chosenOneStr.matches(numRegex)) {
            System.out.println("the introduced value is not valid, please try again.");
            System.out.println("Select the product (insert the number)\n1 - BOX\n2 - FLATBED\n3 - HYBRID");
            chosenOneStr = input.nextLine();
        }
        int chosenOne = Integer.parseInt(chosenOneStr);

        Product product = null;
        while (product == null) {

            switch (chosenOne) {

                case 1 -> product = Product.BOX;
                case 2 -> product = Product.FLATBED;
                case 3 -> product = Product.HYBRID;
                default -> {
                    System.out.println("Invalid number, try again.");
                    chosenOneStr = input.nextLine();
                    if (!chosenOneStr.matches(numRegex)) {
                        System.out.println("Invalid number, try again.");
                        chosenOneStr = input.nextLine();
                    }
                    chosenOne = Integer.parseInt(chosenOneStr);
                }
            }
        }
        Opportunity newOpportunity = new Opportunity();

        return newOpportunity;


    }





    @Override
    public String toString() {
        return String.format(
                "Lead [Id=%d, Name='%s', PhoneNumber='%s', Email='%s', CompanyName='%s', SalesRep='%s']",
                id, name, phoneNumber, email, companyName, salesRepLead.getName());
    }


    //getters
    public Long getId() {
        return id;
    }

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

    public static ArrayList<Lead> getOldLeadList() {
        return oldLeadList;
    }

    public SalesRep getSalesRepLead() {
        return salesRepLead;
    }

    public static ArrayList<Lead> getLeadList() {
        return leadList;
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

    public static void setOldLeadList(ArrayList<Lead> oldLeadList) {
        Lead.oldLeadList = oldLeadList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSalesRepLead(SalesRep salesRepLead) {
        this.salesRepLead = salesRepLead;
    }



    public static void setLeadList(ArrayList<Lead> leadList) {
        Lead.leadList = leadList;
    }

}





















