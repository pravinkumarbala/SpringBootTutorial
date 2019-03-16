package com.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.dao.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	Person findOne(int personId);
	
	List<Person> getPersonInfoByLastName(String lastName);
	
	List<Person> getPersonByFirstNameAndEmailAddr(String firstName, String emailAddr);
	
	//List<Person> findByFirstNameAndEmail(String firstName, String emailAddr);

}