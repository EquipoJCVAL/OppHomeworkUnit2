

package com.Unit2.OppHomeworkUnit2;


import com.Unit2.OppHomeworkUnit2.model.Account;
import com.Unit2.OppHomeworkUnit2.model.Lead;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import com.Unit2.OppHomeworkUnit2.model.SalesRep;
import com.Unit2.OppHomeworkUnit2.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.Scanner;



@SpringBootApplication
public class OppHomeworkUnit2Application{

//    private static final Logger log = LoggerFactory.getLogger(OppHomeworkUnit2Application.class);

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(OppHomeworkUnit2Application.class);

	}
	@Bean
	public CommandLineRunner demo(AccountRepository accountRepository, OpportunityRepository opportunityRepository, LeadRepository leadRepository, SalesRepRepository salesRepRepository) {
		return (args) -> {

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
						for (SalesRep s : salesRepRepository.findAll()) {
							if (s != null) {
								System.out.println(s.toString());
							}

						}
					}
					case "new lead" -> Lead.newLead();

					case "show leads" -> {
						for (Lead l : leadRepository.findAll()) {
							if (l != null) {
								System.out.println(l.toString());
							}
						}
					}
					case "show opportunities" -> {

						for (Opportunity o : opportunityRepository.findAll()) {
							if (o != null) {
								System.out.println(o.toString());
							}
						}
					}

						case "show accounts" -> {

							for (Account a : accountRepository.findAll()) {
								if (a != null) {
									System.out.println(a.toString());
								}
							}
						}

					case "lookup lead" -> {
						if (leadRepository.findById(id).isPresent()) {
							System.out.println(leadRepository.findById(id).get());
						}
					}

					case "lookup opportunity" -> {
						if (opportunityRepository.findById(id).isPresent()) {
							System.out.println(opportunityRepository.findById(id).get());
						}
					}

					case "lookup account" -> {
						if (accountRepository.findById(id).isPresent()) {
							System.out.println(accountRepository.findById(id).get());
						}
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

/*
					// ======================== BY SALES REP =======================================

                case "report lead by salesrep" -> salesRepRepository.countAllLeadsBySalesRep();

                case "report opportunity by salesrep" -> salesRepRepository.countAllOpportunitiesBySalesRep();

                case "report closed-won by salesrep" -> salesRepRepository.countAllClosedWonBySalesRep();

                case "report closed-lost by salesrep" -> salesRepRepository.countAllClosedLostBySalesRep();

                case "report open by salesrep" -> salesRepRepository.countAllOpenBySalesRep();

 */

                // ======================== BY PRODUCT =======================================

                case "report opportunity by the product" ->
                        opportunityRepository.findAllOpportunitiesByProduct();

                case "report closed-won by the product" ->
                        opportunityRepository.findAllClosedWonByProduct();

                case "report closed-lost by the product" ->
                        opportunityRepository.findAllClosedLostByProduct();

                case "report open by the product" ->
                        opportunityRepository.findAllOpenByProduct();

                // ======================== BY COUNTRY =======================================

                case "report opportunity by Country" -> System.out.println("Number of Opportunities: " + opportunityRepository.countAllOpportunitiesByCountry());

                case "report closed-won by Country" -> System.out.println("Number of Closed-Won Opportunities: " + opportunityRepository.countAllClosedWonByCountry());

                case "report closed-lost by Country" -> System.out.println("Number of Closed-Lost Opportunities: " + opportunityRepository.countAllClosedLostByCountry());

                case "report open by Country" -> System.out.println("Number of Opened Opportunities: " + opportunityRepository.countAllOpenByCountry());


                // ======================== BY CITY =======================================

                case "report opportunity by city" -> System.out.println(opportunityRepository.countAllOpportunitiesByCity());

                case "report closed-won by city" ->System.out.println(opportunityRepository.countAllClosedWonByCity());


                case "report closed-lost by city" -> System.out.println(opportunityRepository.countAllClosedLostByCity());

                case "report open by city" -> System.out.println(opportunityRepository.countAllOpenByCity());


                // ======================== BY INDUSTRY =======================================

                case "report opportunity by industry" -> System.out.println(opportunityRepository.countAllByIndustry());

                case "report closed-won by industry" -> System.out.println(opportunityRepository.countAllClosedWonByIndustry());

                case "report closed-lost by industry" -> System.out.println(opportunityRepository.countAllClosedLostByIndustry());

                case "report open by industry" -> System.out.println(opportunityRepository.countAllOpenByIndustry());



                /*
                                ███████ ████████  █████  ████████ ███████ ███████
                                ██         ██    ██   ██    ██    ██      ██
                                ███████    ██    ███████    ██    █████   ███████
                                     ██    ██    ██   ██    ██    ██           ██
                                ███████    ██    ██   ██    ██    ███████ ███████


                 */

                // ======================== EMPLOYEE COUNT STATES =======================================

                case "mean employeecount" -> System.out.println("Mean Employee Count: " + accountRepository.findAverageOfEmployeeCount());

                case "median employeecount" -> System.out.println("Median Employee Count: " + accountRepository.findMedianOfEmployeeCount());

                case "max employeecount" -> System.out.println("Max Employee Count: " + accountRepository.findMaxOfEmployeeCount());

                case "min employeecount" -> System.out.println("Min Employee Count: " + accountRepository.findMinOfEmployeeCount());


                // ======================== QUANTITY STATES =======================================

                case "Mean quantity" -> System.out.println("Mean Quantity: " + opportunityRepository.findAverageOfQuantity());

                case "median quantity" -> System.out.println("Median Quantity: " + opportunityRepository.findMedianOfQuantity());

                case "max quantity" -> System.out.println("Max Quantity: " + opportunityRepository.findMaxOfQuantity());

                case "min quantity" -> System.out.println("Min Quantity: " + opportunityRepository.findMinOfQuantity());


                // ======================== OPPORTUNITY STATES =======================================

					case "mean opps per account" -> System.out.println("Mean Opportunity per Account: " + opportunityRepository.findMinOfQuantity());

                    case "median opps per account" -> System.out.println("Median Opportunity per Account: " + opportunityRepository.findMinOfQuantity());

                    case "max opps per account" -> System.out.println("Max Opportunity per Account: " + opportunityRepository.findMinOfQuantity());

                    case "min opps per account" -> System.out.println("Min Opportunity per Account: " + opportunityRepository.findMinOfQuantity());


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
		};
	}
}

