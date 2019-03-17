package com.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.EnableAsync;

import com.app.dao.Person;
import com.app.dao.Ticket;
import com.app.serviceImplementation.PersonServiceImpl;
import com.app.serviceImplementation.TicketServiceImpl;

@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private TicketServiceImpl tickerServiceImpl;
	
	@Autowired	
	private PersonServiceImpl personServiceImpl;
	
	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		//ConfigurableApplicationContext appContext = 
		app.run(args);
		// TicketServiceImpl tickerServiceImpl = appContext.getBean("tickerServiceImpl", TicketServiceImpl.class);
	}

	@Override
	public void run(String... args) throws Exception {
		// Ticket Management
		createTicket();
		
		// Person Management
		createPersons();
		getPersonByIds();
		deletePersonById(1);
		updatePersonById(2, "marly@compan.com");
		
		// @Query
		getPersonByLastName("Yun");
		getPersonByNameAndEmail("Maria", "marly@company.com");
		
		searchByFirstOrLastName("Lydia", "Sei");
		
		// Page and Sort
		List<Person> personList = personServiceImpl.findByLastName("Lydia", new PageRequest(0, 4, Direction.ASC, "firstName"));
		personList.forEach(System.out::println);
		
		asynQuery("marly@company.com");
		
		updatePersonEmailAddr(3, "seiyun@ekalaivan.com");
		
	}
	
	private void updatePersonEmailAddr(int id, String emailAddr) {
		personServiceImpl.updatePersonEmailAddr(id, emailAddr);
	}

	private void asynQuery(String emailAddr) throws Exception {
		CompletableFuture<Person> completableFuture = personServiceImpl.getByEmaiAddr(emailAddr);
		Person person = completableFuture.get(20, TimeUnit.SECONDS);
		System.out.println(person);
	}

	private void searchByFirstOrLastName(String lastName, String firstName) {
		List<Person> searchList = personServiceImpl.searchByFirstOrLastName(lastName, firstName);
		searchList.forEach(System.out::println);
	}

	private void getPersonByNameAndEmail(String name, String email) {
		Iterable<Person> personByNameAndEmail = personServiceImpl.getPersonByFirstNameAndEmail(name, email);
		personByNameAndEmail.forEach(System.out::println);
	}

	private void getPersonByLastName(String name) {
		Iterable<Person> personByLastName = personServiceImpl.getPersonInfoByLastName(name);
		personByLastName.forEach(System.out::println);
	}

	private void updatePersonById(int personId, String email) {
		Person updPerson = new Person();
		updPerson.setPersonId(personId);
		updPerson.setEmailAddr(email);
		personServiceImpl.updatePersonEntity(updPerson);
	}

	public void getPersonByIds() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		Iterable<Person> personList = personServiceImpl.getPersonsByIds(ids);
		personList.forEach(System.out::println);
	}
	
	public void deletePersonById(int personId) {
		Person delPerson = new Person();
		delPerson.setPersonId(personId);
		personServiceImpl.deletePersonEntity(delPerson);
	}
	
	public void createPersons() {
		List<Person> personList = Arrays.asList(new Person("Pravin","Kumar", "praku@company.com", new Date()),
				new Person("Maria","Lydia", "marly@company.com", new Date()),
				new Person("Sei","Yun", "seiyun@company.com", new Date()));
		Iterable<Person> createdPersons = personServiceImpl.createPersons(personList);
		for(Person per : createdPersons)
			log.info("Perons :: " + per);
	}
	
	public void createPerson() {
		Person person = new Person("Pravin","Kumar", "praku@company.com", new Date());
		Person personDb = personServiceImpl.createPerson(person);
		log.info("Person Db :: " + personDb);;
	}
	
	public void createTicket() {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("Praku");
		ticket.setEmailAddr("praku@comp.com");
		ticket.setBookingDate(new Date());
		ticket.setSrcStation("Chennai");
		ticket.setDestStation("Dindigul");
		tickerServiceImpl.createTicket(ticket);
		log.info("Datasorce :: " + dataSource);
	}

}