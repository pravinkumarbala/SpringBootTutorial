package com.app.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.Person;
import com.app.repository.PersonRepository;
import com.app.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person createPerson(Person person) {
		return personRepository.save(person);	
	}

	@Override
	public Iterable<Person> createPersons(List<Person> personList) {
		return personRepository.saveAll(personList);
	}

	@Override
	public Iterable<Person> getPersonsByIds(List<Integer> ids) {
		return personRepository.findAllById(ids);
	}

	@Override
	public void deletePersonEntity(Person delPerson) {
		personRepository.delete(delPerson);
	}

	@Override
	public void updatePersonEntity(Person updPerson) {
		Person update = personRepository.findOne(updPerson.getPersonId());
		if(updPerson.getPersonId() == update.getPersonId())
			update.setEmailAddr(updPerson.getEmailAddr());
		personRepository.save(updPerson);
	}

	@Override
	public List<Person> getPersonInfoByLastName(String lastName) {
		return personRepository.getPersonInfoByLastName(lastName);
	}

}