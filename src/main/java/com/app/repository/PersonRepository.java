package com.app.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.websocket.server.PathParam;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

	Person findByPersonId(int personId);
	
	// @Query(value = "SELECT p from Person p WHERE p.lastName = ?1")
	//@Query(value = "SELECT * from Person WHERE Last_Name = ?1", nativeQuery = true)
	@Query(value = "SELECT p from Person p WHERE p.lastName = :lastName")
	List<Person> getPersonInfoByLastName(@PathParam("lastName") String lastName);
	
	// @Query(value = "SELECT p from Person p WHERE p.firstName = ?1 AND p.emailAddr = ?2")
	@Query(value = "SELECT * from Person WHERE First_Name = ?1 AND Email_Addr = ?2", nativeQuery = true)
	List<Person> getPersonByFirstNameAndEmailAddr(String firstName, String emailAddr);

	List<Person> findByLastName(String lastName, Pageable pageable);

	@Async
	CompletableFuture<Person> findByEmailAddr(String emailAddr);
	
	@Query(value = "SELECT p FROM Person p WHERE p.firstName = :firstName Or p.lastName = :lastName")
	List<Person> findByFirstNameOrLastName(@PathParam("lastName") String lastName, @PathParam("firstName") String firstName);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE Person set emailAddr = :emailAddr WHERE id = :id")
	void updatePersonEmailAddr(@Param("id") int id, @Param("emailAddr") String emailAddr);
	
}