package com.app.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Person")
@DynamicUpdate
@NamedQueries(value = {
		@NamedQuery(name = "Person.getPersonInfoByLastName", query = "SELECT p from Person p WHERE p.lastName = ?1"),
		@NamedQuery(name = "Person.getPersonByFirstNameAndEmailAddr", query = "SELECT p from Person p WHERE p.firstName = ?1 AND p.emailAddr = ?2")
})
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Person_Id")
	private int personId;
	
	@Column(name = "First_Name", length = 60, nullable = false)
	private String firstName;
	
	@Column(name = "Last_Name", length = 60, nullable = false)
	private String lastName;
	
	@Column(name = "Email_Address", unique = true)
	private String emailAddr;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "Creation_Date")
	private Date creationDate;
	
	public Person() { }

	public Person(String firstName, String lastName, String emailAddr, Date creationDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddr = emailAddr;
		this.creationDate = creationDate;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddr="
				+ emailAddr + ", creationDate=" + creationDate + "]";
	}
	
}