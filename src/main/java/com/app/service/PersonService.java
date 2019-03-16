package com.app.service;

import java.util.List;

import com.app.dao.Person;

public interface PersonService {
	public Person createPerson(Person person);
	public Iterable<Person> createPersons(List<Person> personList);
	public Iterable<Person> getPersonsByIds(List<Integer> ids);
	public void deletePersonEntity(Person delPerson);
	public void updatePersonEntity(Person updPerson);
	
	public List<Person> getPersonInfoByLastName(String lastName);
}