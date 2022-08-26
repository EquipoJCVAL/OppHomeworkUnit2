package com.Unit2.OppHomeworkUnit2.model;

import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.Unit2.OppHomeworkUnit2.model.Enums.Product;
import com.Unit2.OppHomeworkUnit2.model.Enums.Status;
import com.Unit2.OppHomeworkUnit2.repository.LeadRepository;

import javax.persistence.*;
import java.util.*;

@Entity
@Table (name = "lead_")
public class Lead {

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




    public static void newLead() {


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

                //leadRepository.save(newLead);
                leadList.add(newLead);

    }


    public static void convertID(Long idNum) {
        String wordRegex = "([A-Z][a-z]+([ ]?[a-z]?['-]?)*)+";
        String numRegex = "[^a-z ]*([.0-9])*\\d";

        Scanner input = new Scanner(System.in);
        boolean found = false;

        List<Opportunity> opportunityList = new ArrayList<>();
        List<Contact> contactList = new ArrayList<>();


        //Since it's a long process, this allows the user to interrupt it from the beginning
        System.out.println("Press Enter to process the conversion or type 'exit' to quit.");
        String exit = input.nextLine();
        while(!found) {

            for (int i = 0; i < leadList.size(); i++) {
                if (leadList.get(i).getId() == idNum) {

                    found = true;

                    while (!Objects.equals(exit, "exit")) {

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
                        //Creates a new Contact with the Lead's data, adds it and Opportunity in the respective lists
                        Contact contact = new Contact(leadList.get(i).getName(), leadList.get(i).getPhoneNumber(), leadList.get(i).getEmail(), leadList.get(i).getCompanyName());
                        contactList.add(contact);
                        Opportunity opportunity = new Opportunity(product, truckNum, contact, Status.OPEN);
                        Opportunity.opportunitiesList.add(opportunity);
                        opportunityList.add(opportunity);

                        //Account info
                        input.nextLine();
                        System.out.println("Opportunity successfully created! To complete the process you must create an Account.");
                        System.out.println("City name: ");
                        String city = input.nextLine();

                        while (!city.matches(wordRegex)) {
                            System.out.println("Please, insert a valid city name capitalized (for example 'New York'): ");
                            city = input.nextLine();
                        }

                        System.out.println("Country of the organization: ");
                        String country = input.nextLine();

                        while (!country.matches(wordRegex)) {
                            System.out.println("Please, insert a valid country name with the first letter capitalized: ");
                            country = input.nextLine();
                        }

                        System.out.println("Number of employees: ");
                        String employeeStr = input.nextLine();

                        while (!employeeStr.matches(wordRegex)) {
                            System.out.println("The value introduced is not a valid number, insert a valid value");
                            employeeStr = input.nextLine();

                        }
                        int employees = Integer.parseInt(employeeStr);

                        System.out.println("Select the product (insert the number)\n1 - ECOMMERCE\n2 - MANUFACTURING\n3 - MEDICAL\n4 - PRODUCE\n5 - OTHER");
                        String chosenTwo = input.nextLine();

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
                                    chosenTwo = input.nextLine();
                                }
                            }
                        }

                        //Creates a new Account and a list for Contact and Opportunity
                        Account account = new Account(industry, employees, city, country, contactList, opportunityList);

                        //Add Lead to another list and delete it from the current one
                        oldLeadList.add(leadList.get(i));
                        leadList.remove(leadList.get(i));

                        }
                    System.out.println("Account Created!\n");
                    break;
                }
            }
        }
        // ID not found
        if (!found && !exit.equals("exit")) {
            System.out.println("This Id doesn't match with any Lead, it could have been already converted into a Opportunity, you can verify typing 'Show Opportunities' in the main Menu, otherwise try again with the correct Id.");
        }
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
}