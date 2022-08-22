package com.Unit2.OppHomeworkUnit2.model.MenuInput;

import com.Unit2.OppHomeworkUnit2.model.Account;
import com.Unit2.OppHomeworkUnit2.model.Lead;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;

import java.util.Objects;
import java.util.Scanner;

public class Menu {

    static int id;
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

        System.out.println("List of available commands:\n- New Lead\n- Show Leads\n- Show Opportunities\n- Show Accounts" +
                "\n- Lookup Lead ID\n- Lookup Opportunity ID\n- Lookup Account ID\n- Convert ID\n- Close Lost ID\n- Close Won ID\n- Help\n- Exit");

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
                }catch(NumberFormatException e){
                    command = "EXCEPTION";
                }


                command = splitCommand[0] + " " + splitCommand[1];

            } else if (command.contains("convert")) {
                String[] splitCommand = command.split(" ");
                command = splitCommand[0];
                try {
                    id = Long.parseLong(splitCommand[splitCommand.length - 1]);
                }catch(NumberFormatException e){
                    command = "EXCEPTION";
                    //En el caso que no se haya introducido un ID correcto saltara una excepcion
                }

            }

            //Dependiendo de el comand introducido el switch llamara a los metodos que corresponden segun su comando
            switch (command) {

                case "new lead" -> Lead.newLead();

                case "show leads" -> Lead.showLeads();

                case "show opportunities" -> Opportunity.showOpportunities();

                case "show accounts" -> Account.showAccounts();

                case "lookup lead" -> Lead.lookUpLead(id);

                case "lookup opportunity" -> Opportunity.lookUpOpportunity(id);

                case "lookup account" -> Account.lookUpAccount(id);

                case "convert" -> Lead.convertID(id);

                case "close lost" -> Opportunity.closeLost(id);

                case "close won" -> Opportunity.closeWon(id);

                case "help" -> System.out.println("List of available commands:\n- New Lead\n- Show Leads\n- " +
                        "Show Opportunities\n- Show Accounts\n- Lookup Lead ID\n- Lookup Opportunity ID\n- Lookup Account ID" +
                        "\n- Convert ID\n- Close Lost ID\n- Close Won ID\n- Exit");

                case "EXCEPTION" -> System.err.println("Invalid Id number.");

                default -> {
                    if (!command.equals("exit")) System.out.println("The Command doesn't exist try again");

                }
            }
        }
    }
}














