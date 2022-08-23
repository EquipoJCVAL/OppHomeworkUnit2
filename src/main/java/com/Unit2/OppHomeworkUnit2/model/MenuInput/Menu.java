package com.Unit2.OppHomeworkUnit2.model.MenuInput;

import com.Unit2.OppHomeworkUnit2.model.Lead;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;

import java.util.Objects;
import java.util.Scanner;

import com.Unit2.OppHomeworkUnit2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;


public class Menu {
    @Autowired
    static AccountRepository accountRepository;
    @Autowired
    static OpportunityRepository opportunityRepository;
    @Autowired
    static LeadRepository leadRepository;
    @Autowired
    static SalesRepRepository salesRepRepository;



    public static void start() throws ClassNotFoundException {


        System.out.println("     _______.     ___       __       _______     _______.   .___  ___.      ___      .__   __.      ___       _______  _______ .______      \n" +
                "    /       |    /   \\     |  |     |   ____|   /       |   |   \\/   |     /   \\     |  \\ |  |     /   \\     /  _____||   ____||   _  \\     \n" +
                "   |   (----`   /  ^  \\    |  |     |  |__     |   (----`   |  \\  /  |    /  ^  \\    |   \\|  |    /  ^  \\   |  |  __  |  |__   |  |_)  |    \n" +
                "    \\   \\      /  /_\\  \\   |  |     |   __|     \\   \\       |  |\\/|  |   /  /_\\  \\   |  . `  |   /  /_\\  \\  |  | |_ | |   __|  |      /     \n" +
                ".----)   |    /  _____  \\  |  `----.|  |____.----)   |      |  |  |  |  /  _____  \\  |  |\\   |  /  _____  \\ |  |__| | |  |____ |  |\\  \\----.\n" +
                "|_______/    /__/     \\__\\ |_______||_______|_______/       |__|  |__| /__/     \\__\\ |__| \\__| /__/     \\__\\ \\______| |_______|| _| `._____|\n" +
                "                                                                                                                                            ");


        Scanner input = new Scanner(System.in);

        String exit = null;

        Long id = 0L;

        System.out.println("List of available commands:\n- New SalesRep\n- New Lead\n- Show SalesReps/Leads/Opportunities/Accounts\n" +
                "- Lookup Lead/Opportunity/Account ID\n- Convert ID\n- Close Lost/Won ID\n- Help Reports/States\n- Help\n- Exit");

        //Creamos un loop indeterminado el cual parara de ejecutarse si el objeto exit contiene "exit"
        while (!Objects.equals(exit, "exit")) {


            //Generamos una variable la cual relacionamos con el scanner haciendolo case sensitive
            String command = input.nextLine().toLowerCase().trim();

            //Igualamos command con exit para que si en algun momento el usuario introdujera exit, irrumpiria el loop
            exit = command;

            //Aqui hacemos un split de los comandos siguientes para poder extraer el id de los strings introducidos por el usuario
            if (command.contains("lookup account") || command.contains("lookup opportunity") ||
                    command.contains("lookup lead") || command.contains("close lost") || command.contains("close won")) {
                String[] splitCommand = command.split(" ");
                try {
                    id = Long.parseLong(splitCommand[splitCommand.length - 1]);
                } catch (NumberFormatException e) {
                    command = "EXCEPTION";
                }


                command = splitCommand[0] + " " + splitCommand[1];

            } else if (command.contains("convert")) {
                String[] splitCommand = command.split(" ");
                command = splitCommand[0];
                try {
                    id = Long.parseLong(splitCommand[splitCommand.length - 1]);
                } catch (NumberFormatException e) {
                    command = "EXCEPTION";
                    //En el caso que no se haya introducido un ID correcto saltara una excepcion
                }

            }

            //Dependiendo de el comand introducido el switch llamara a los metodos que corresponden segun su comando
            switch (command) {
                case "new salesrep" -> System.out.println();

                case "show salesreps" -> {
                    try {
                        System.out.println(salesRepRepository.findAll());
                    } catch (Exception e) {
                        System.out.println("No records found.");
                    }
                }
                case "new lead" -> Lead.newLead();

                case "show leads" -> {
                    try {
                        leadRepository.findAll();
                    } catch (Exception e) {
                        System.out.println("No records found.");
                    }
                }
                case "show opportunities" -> {
                    try {
                        opportunityRepository.findAll();
                    } catch (Exception e) {
                        System.out.println("No records found.");
                    }
                }

                case "show accounts" -> {
                    try {
                        accountRepository.findAll();
                    } catch (Exception e) {
                        System.out.println("No records found.");
                    }
                }

                case "lookup lead" -> {
                    if (leadRepository.findById(id).isPresent()){
                        leadRepository.findById(id);
                    }
                    System.out.println("No records found.");


                }

                case "lookup opportunity" ->  {
                    try {
                        opportunityRepository.findById(id);
                    } catch (Exception e) {
                        System.out.println("No records found.");
                    }
                }

                case "lookup account" -> {
                    if (accountRepository.findById(id).isPresent()) {
                        accountRepository.findById(id);
                    }
                    System.out.println("No records found.");
                }

                case "convert" -> Lead.convertID(id);

                case "close lost" -> Opportunity.closeLost(id);

                case "close won" -> Opportunity.closeWon(id);


                /*

                            ██████╗ ███████╗██████╗  ██████╗ ██████╗ ████████╗███████╗
                            ██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██╔════╝
                            ██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝   ██║   ███████╗
                            ██╔══██╗██╔══╝  ██╔═══╝ ██║   ██║██╔══██╗   ██║   ╚════██║
                            ██║  ██║███████╗██║     ╚██████╔╝██║  ██║   ██║   ███████║
                            ╚═╝  ╚═╝╚══════╝╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝

                 */


                // ======================== BY SALES REP =======================================
/*
                case "report lead by salesrep" -> {
                    try{
                        salesRepRepository.countAllLeadsBySalesRep();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report opportunity by salesrep" -> {
                    try{
                        salesRepRepository.ountAllOpportunitiesBySalesRep();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-won by salesrep" -> {
                    try{
                        salesRepRepository.countAllClosedWonBySalesRep();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-lost by salesrep" -> {
                    try{
                        salesRepRepository.countAllClosedLostBySalesRep();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report open by salesrep" -> {
                    try{
                        salesRepRepository.countAllOpenBySalesRep();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }

                // ======================== BY PRODUCT =======================================

                case "report opportunity by the product" -> {
                    try{
                        opportunityRepository.findAllOpportunitiesByProduct();;
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-won by the product" -> {
                    try{
                        opportunityRepository.findAllClosedWonByProduct();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-lost by the product" -> {
                    try{
                        opportunityRepository.findAllClosedLostByProduct();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report open by the product" -> {
                    try{
                        opportunityRepository.findAllOpenByProduct();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                // ======================== BY COUNTRY =======================================

                case "report opportunity by Country" -> {
                    try{
                        accountRepository.countAllOpportunitiesByCountry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-won by Country" -> {
                    try{
                        accountRepository.countAllClosedWonByCountry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-lost by Country" -> {
                    try{
                        accountRepository.countAllClosedLostByCountry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report open by Country" -> {
                    try{
                        accountRepository.countAllOpenByCountry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }


                // ======================== BY CITY =======================================
                case "report opportunity by city" -> {
                    try{
                        accountRepository.countAllOpportunitiesByCity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-won by city" -> {
                    try{
                        accountRepository.countAllClosedWonByCity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-lost by city" -> {
                    try{
                        accountRepository.countAllClosedLostByCity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report open by city" -> {
                    try{
                        accountRepository.countAllOpenByCity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }


                // ======================== BY INDUSTRY =======================================
                case "report opportunity by industry" -> {
                    try{
                        accountRepository.countAllByIndustry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-won by industry" -> {
                    try{
                        accountRepository.countAllClosedWonByIndustry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report closed-lost by industry" -> {
                    try{
                        accountRepository.countAllClosedLostByIndustry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "report open by industry" -> {
                    try{
                        accountRepository.countAllOpenByIndustry();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }

 */

                /*
                                ███████ ████████  █████  ████████ ███████ ███████
                                ██         ██    ██   ██    ██    ██      ██
                                ███████    ██    ███████    ██    █████   ███████
                                     ██    ██    ██   ██    ██    ██           ██
                                ███████    ██    ██   ██    ██    ███████ ███████


                 */
/*
                // ======================== EMPLOYEE COUNT STATES =======================================
                case "mean employeecount" -> {
                    try{
                        accountRepository.findAverageOfEmployeeCount();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "median employeecount" -> {
                    try{
                        accountRepository.findMedianOfEmployeeCount();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "max employeecount" -> {
                    try{
                        accountRepository.findMaxOfEmployeeCount();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "min employeecount" -> {
                    try{
                        accountRepository.findMinOfEmployeeCount();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }

                // ======================== QUANTITY STATES =======================================

                case "Mean quantity" -> {
                    try{
                        opportunityRepository.findAverageOfQuantity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "median quantity" ->{
                    try{
                        opportunityRepository.findMedianOfQuantity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "max quantity" -> {
                    try{
                        opportunityRepository.findMaxOfQuantity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }
                case "min quantity" -> {
                    try{
                        opportunityRepository.findMinOfQuantity();
                    }catch (Exception e){
                        System.out.println("No records found.");
                    }
                }

                // ======================== OPPORTUNITY STATES =======================================

                    case "mean opps per account" -> {
                        try{
                            opportunityRepository.findMinOfQuantity();
                        }catch (Exception e){
                            System.out.println("No records found.");
                        }
                    }
                    case "median opps per account" -> {
                        try{
                            opportunityRepository.findMinOfQuantity();
                        }catch (Exception e){
                            System.out.println("No records found.");
                        }
                    }
                    case "max opps per account" -> {
                        try{
                            opportunityRepository.findMinOfQuantity();
                        }catch (Exception e){
                            System.out.println("No records found.");
                        }
                    }
                    case "min opps per account" -> {
                        try{
                            opportunityRepository.findMinOfQuantity();
                        }catch (Exception e){
                            System.out.println("No records found.");
                        }
                    }


*/
                // ======================== HELP =======================================

                case "help" -> System.out.println("List of available commands:\n- New SalesRep\n- New Lead\n- Show SalesReps/Leads/Opportunities/Accounts\n" +
                        "- Lookup Lead/Opportunity/Account ID\n- Convert ID\n- Close Lost/Won ID\n- Help Reports/States\n- Help\n- Exit");
                case "help reports" -> {
                    System.out.println("====INSERT ONE OPTION LISTED BELOW IN THE RESPECTIVE COMMAND====\n- Opportunity\n- CLOSED-LOST\n- CLOSED-WON\n- OPEN\n");
                    System.out.println("======COMMANDS=====");
                    System.out.println("Sales Reports:\n- Report OPTION by SalesRep\n");
                    System.out.println("Product Reports: \n- Report OPTION by the product\n");
                    System.out.println("Country Reports: \n- Report OPTION by Country\n");
                    System.out.println("City Reports: \n- Report Opportunity by City\n");
                    System.out.println("Industry Reports: \n- Report OPTION by Industry\n");
                }
                case "help states" -> {

                    System.out.println("====INSERT ONE OPTION LISTED BELOW IN THE RESPECTIVE COMMAND====\n- Mean\n- Median\n- Max\n- Min\n");
                    System.out.println("======COMMANDS=====");
                    System.out.println("Employee States: \n- OPTION EmployeeCount\n");
                    System.out.println("Quantity States: \n- OPTION Quantity\n");
                    System.out.println("Opportunity States: \n- OPTION Opps per Account\n");

                }

                // ======================== EXCEPTIONS =======================================

                case "EXCEPTION" -> System.err.println("Invalid Id number.");

                default -> {
                    if (!command.equals("exit")) System.out.println("The Command doesn't exist, try again");

                }
            }
        }
    }
}




