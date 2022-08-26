

package com.Unit2.OppHomeworkUnit2;

import com.Unit2.OppHomeworkUnit2.model.Lead;
import com.Unit2.OppHomeworkUnit2.model.Opportunity;
import com.Unit2.OppHomeworkUnit2.model.SalesRep;
import com.Unit2.OppHomeworkUnit2.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Objects;
import java.util.Scanner;



@SpringBootApplication
public class OppHomeworkUnit2Application{

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

					case "new lead" -> leadRepository.save(Lead.newLead());

					case "convert" -> SalesRep.newSalesRep(); leadRepository.findById().convertID(lead);

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

					// ======================== FIND ALL =======================================

					case "show salesreps" -> {
						salesRepRepository.findAll().forEach(salesRep -> {
							if (salesRep != null) System.out.println(salesRep.toString());
						});
					}

					case "show leads" -> {
						leadRepository.findAll().forEach(lead -> {
							if (lead != null) System.out.println(lead.toString());
						});
					}

					case "show opportunities" -> {
						opportunityRepository.findAll().forEach(opp -> {
							if (opp != null) System.out.println(opp.toString());
						});
					}

					case "show accounts" -> {
						accountRepository.findAll().forEach(acc -> {
							if (acc != null) System.out.println(acc.toString());
						});
					}

					// ======================== FIND BY ID =======================================

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



					// ======================== BY SALES REP =======================================

					/*
					case "report lead by salesrep" -> salesRepRepository.countAllLeadsBySalesRep();

					case "report opportunity by salesrep" -> salesRepRepository.countAllOpportunitiesBySalesRep();

					case "report closed-won by salesrep" -> salesRepRepository.countAllClosedWonBySalesRep();

					case "report closed-lost by salesrep" -> salesRepRepository.countAllClosedLostBySalesRep();

					case "report open by salesrep" -> salesRepRepository.countAllOpenBySalesRep();

					 */

					// ======================== BY PRODUCT =======================================

					case "report opportunity by the product" -> {
						for(int i = 0; i < opportunityRepository.findAllOpportunitiesByProduct().length; i++){
							System.out.println(opportunityRepository.findAllOpportunitiesByProduct()[i]);
						}
					}

					case "report closed-won by the product" -> {
						for(int i = 0; i < opportunityRepository.findAllClosedWonByProduct().length; i++){
							System.out.println(opportunityRepository.findAllClosedWonByProduct()[i]);
						}
					}

					case "report closed-lost by the product" -> {
						for(int i = 0; i < opportunityRepository.findAllClosedLostByProduct().length; i++){
							System.out.println(opportunityRepository.findAllClosedLostByProduct()[i]);
						}
					}

					case "report open by the product" -> {
						for(int i = 0; i < opportunityRepository.findAllOpenByProduct().length; i++){
							System.out.println(opportunityRepository.findAllOpenByProduct()[i]);
						}
					}

					// ======================== BY COUNTRY =======================================

					case "report opportunity by country" -> {
						for(int i = 0; i < opportunityRepository.countAllOpportunitiesByCountry().length; i++){
							System.out.println(opportunityRepository.countAllOpportunitiesByCountry()[i]);
						}
					}

					case "report closed-won by country" -> {
						for(int i = 0; i < opportunityRepository.countAllClosedWonByCountry().length; i++){
							System.out.println(opportunityRepository.countAllClosedWonByCountry()[i]);
						}
					}

					case "report closed-lost by country" -> {
						for(int i = 0; i < opportunityRepository.countAllClosedLostByCountry().length; i++){
								System.out.println(opportunityRepository.countAllClosedLostByCountry()[i]);
						}
					}

					case "report open by country" -> {
						for(int i = 0; i < opportunityRepository.countAllOpenByCountry().length; i++){
								System.out.println(opportunityRepository.countAllOpenByCountry()[i]);
						}
					}



						// ======================== BY CITY =======================================

					case "report opportunity by city" -> {
						for(int i = 0; i < opportunityRepository.countAllOpportunitiesByCity().length; i++){
							System.out.println(opportunityRepository.countAllOpportunitiesByCity()[i]);
						}
					}


					case "report closed-won by city" ->{
						for(int i = 0; i < opportunityRepository.countAllClosedWonByCity().length; i++){
							System.out.println(opportunityRepository.countAllClosedWonByCity()[i]);
						}
					}


					case "report closed-lost by city" -> {
						for(int i = 0; i < opportunityRepository.countAllClosedLostByCity().length; i++){
							System.out.println(opportunityRepository.countAllClosedLostByCity()[i]);
						}
					}

					case "report open by city" -> {
						for(int i = 0; i < opportunityRepository.countAllOpenByCity().length; i++){
							System.out.println(opportunityRepository.countAllOpenByCity()[i]);
						}
					}


					// ======================== BY INDUSTRY =======================================

					case "report opportunity by industry" -> {
						for(int i = 0; i < opportunityRepository.countAllOpenByIndustry().length; i++){
							System.out.println(opportunityRepository.countAllByIndustry()[i]);
						}
					}

					case "report closed-won by industry" -> {
						for(int i = 0; i < opportunityRepository.countAllClosedWonByIndustry().length; i++){
							System.out.println(opportunityRepository.countAllClosedWonByIndustry()[i]);
						}
					}

					case "report closed-lost by industry" -> {
						for(int i = 0; i < opportunityRepository.countAllClosedLostByIndustry().length; i++){
							System.out.println(opportunityRepository.countAllClosedLostByIndustry()[i]);
						}
					}

					case "report open by industry" -> {
						for(int i = 0; i < opportunityRepository.countAllOpenByIndustry().length; i++){
							System.out.println(opportunityRepository.countAllOpenByIndustry()[i]);
						}
					}



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

					case "mean quantity" -> System.out.println("Mean Quantity: " + opportunityRepository.findAverageOfQuantity());

					case "median quantity" -> System.out.println("Median Quantity: " + opportunityRepository.findMedianOfQuantity());

					case "max quantity" -> System.out.println("Max Quantity: " + opportunityRepository.findMaxOfQuantity());

					case "min quantity" -> System.out.println("Min Quantity: " + opportunityRepository.findMinOfQuantity());

				// ======================== OPPORTUNITY STATES =======================================

					case "mean opps per account" -> System.out.println("Mean Opportunity per Account: " + opportunityRepository.findAveragePerAccount());

//					case "median opps per account" -> System.out.println("Median Opportunity per Account: " + opportunityRepository.findMedianPerAccount());

					case "max opps per account" -> System.out.println("Max Opportunity per Account: " + opportunityRepository.findMaxPerAccount());

					case "min opps per account" -> System.out.println("Min Opportunity per Account: " + opportunityRepository.findMinPerAccount());



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
						if (!command.equals("exit") && !command.equals("")) System.out.println("The Command doesn't exist, try again");

					}

				}




			}


		};


	}

}

