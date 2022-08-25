

package com.Unit2.OppHomeworkUnit2;


import com.Unit2.OppHomeworkUnit2.model.*;
import com.Unit2.OppHomeworkUnit2.model.Enums.Industry;
import com.Unit2.OppHomeworkUnit2.model.Enums.Product;
import com.Unit2.OppHomeworkUnit2.model.Enums.Status;
import com.Unit2.OppHomeworkUnit2.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OppHomeworkUnit2ApplicationTests {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	LeadRepository leadRepository;
	@Autowired
	OpportunityRepository opportunityRepository;
	@Autowired
	SalesRepRepository salesRepRepository;



	// SalesReps


	SalesRep salesRep1 = new SalesRep("Elon Musk");
	SalesRep salesRep2 = new SalesRep("Bill Gates");

	SalesRep salesRep3 = new SalesRep("Pino Pinello");
	SalesRep salesRep4 = new SalesRep("Barbara Letta");
	SalesRep salesRep5 = new SalesRep("Jonny");

	// Leads

	Lead lead1 = new Lead("Elon Musk", "3123414321", "tesla@tesla.com", "Tesla", salesRep1);
	Lead lead2 = new Lead("Bill Gates", "654 345 542", "billy@microsoft.com", "Microsoft", salesRep2);
	Lead lead3 = new Lead("Steve Jobs", "989 54 34 21345", "steve@apple.com", "Apple", salesRep5);
	Lead lead4 = new Lead("Pino Pinello", "1234 543 654", "pini@pinelli.com", "Pinello Company", salesRep4);
	Lead lead5 = new Lead("Barbara Letta", "989 345823 4329", "letta@lettata.com", "Latteria", salesRep4);
	// Accounts
	List<Contact> contacts = new ArrayList<>();
	List<Opportunity> opportunities = new ArrayList<>();
	Account account1 = new Account(Industry.ECOMMERCE, 56, "Paris", "France", contacts, opportunities);
	Account account2 = new Account(Industry.PRODUCE, 20, "London", "England", contacts, opportunities);
	Account account3 = new Account(Industry.MANUFACTURING, 24, "Berlin", "Germany", contacts, opportunities);
	Account account4 = new Account(Industry.MEDICAL, 13, "Helsinky", "Finland", contacts, opportunities);

	// Contacts
	Contact contact1 = new Contact("Pino Pinello", "1234 543 654", "pini@pinelli.com", "Pinello Company", account1);
	Contact contact2 = new Contact("Barbara Letta", "989 345823 4329", "letta@lettata.com", "Latteria", account2);

	// Opportunities
	Opportunity opportunity1 = new Opportunity(Product.BOX, 43, contact1, Status.OPEN, account1, salesRep3);
	Opportunity opportunity2 = new Opportunity(Product.HYBRID, 35, contact2, Status.CLOSED_LOST, account2, salesRep4);
	Opportunity opportunity3 = new Opportunity(Product.FLATBED, 13, contact2, Status.CLOSED_WON, account2, salesRep4);


	@BeforeEach
	void setUp() {
		salesRepRepository.saveAll(asList(salesRep1,salesRep2,salesRep3, salesRep4, salesRep5));
		leadRepository.saveAll(asList(lead1, lead2, lead3, lead4, lead5));
		accountRepository.saveAll(asList(account1, account2, account3, account4));
		contactRepository.saveAll(asList(contact1, contact2));
		opportunityRepository.saveAll(asList(opportunity1, opportunity2, opportunity3));
	}

	@AfterEach
	void tearDown() {
//        accountRepository.deleteAll();
//        opportunityRepository.deleteAll();
//        leadRepository.deleteAll();
//        contactRepository.deleteAll();
	}

	// =============== EMPLOYEE COUNT STATES TESTS ===============

	@Test
	void findAverageOfEmployeeCount() {
		assertEquals(28, accountRepository.findAverageOfEmployeeCount());
	}

	@Test
	void findMedianOfEmployeeCount() {
		assertEquals(38, accountRepository.findMedianOfEmployeeCount());

	}

	@Test
	void findMaxOfEmployeeCount() {
		assertEquals(56, accountRepository.findMaxOfEmployeeCount());
	}

	@Test
	void findMinOfEmployeeCount() {
		assertEquals(13, accountRepository.findMinOfEmployeeCount());
	}


	// ============   QUANTITY  TESTS ==================

	@Test
	void findAverageOfQuantity() {
		assertEquals(30, opportunityRepository.findAverageOfQuantity());
	}

	@Test
	void findMedianOfQuantity() {
		assertEquals(33, opportunityRepository.findMedianOfQuantity());

	}

	@Test
	void findMaxOfQuantity() {
		assertEquals(43, opportunityRepository.findMaxOfQuantity());
	}

	@Test
	void findMinOfQuantity() {
		assertEquals(13, opportunityRepository.findMinOfQuantity());
	}

	// ============   BY COUNTRY TESTS ==================

	@Test
	void countAllOpportunitiesByCountry() {


	}


}
