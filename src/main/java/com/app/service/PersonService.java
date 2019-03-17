package com.app.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.PageRequest;

import com.app.dao.Person;

public interface PersonService {
	public Person createPerson(Person person);
	public Iterable<Person> createPersons(List<Person> personList);
	public Iterable<Person> getPersonsByIds(List<Integer> ids);
	public void deletePersonEntity(Person delPerson);
	public void updatePersonEntity(Person updPerson);
	
	public List<Person> getPersonInfoByLastName(String lastName);
	public List<Person> getPersonByFirstNameAndEmail(String name, String email);
	
	public List<Person> findByLastName(String string, PageRequest pageRequest);
	
	public CompletableFuture<Person> getByEmaiAddr(String emailAddr);
	
	public List<Person> searchByFirstOrLastName(String lastName, String firstName);
	
	public void updatePersonEmailAddr(int id, String emailAddr);
}