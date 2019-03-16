package com.app.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TicketId")
	private int ticketId;

	@Column(name = "PassengerName", nullable = false)
	private String passengerName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "BookingDate")
	private Date bookingDate;

	@Column(name = "Source")
	private String srcStation;

	@Column(name = "Destination")
	private String destStation;

	@Column(name = "Email")
	private String emailAddr;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSrcStation() {
		return srcStation;
	}

	public void setSrcStation(String srcStation) {
		this.srcStation = srcStation;
	}

	public String getDestStation() {
		return destStation;
	}

	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

}