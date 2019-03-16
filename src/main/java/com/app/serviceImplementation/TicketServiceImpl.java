package com.app.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.Ticket;
import com.app.repository.TicketRepository;
import com.app.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	/*
	 * Ticket Information
	 *
	 * @returns all tickets information
	 */
	@Override
	public Iterable<Ticket> fetchTicketDB() {
		return ticketRepository.findAll();
	}

	/*
	 * Booking Ticket
	 *
	 * @Param ticket model
	 *
	 * @returns boolean value
	 */
	@Override
	public Ticket createTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	/*
	 * Ticket Booked Information
	 *
	 * @Param ticket id
	 *
	 * @returns ticket information
	 */
	@Override
	public Ticket fetchTicketDetail(int ticketId) {
		return ticketRepository.findByTicketId(ticketId);
	}

	/*
	 * Delete Ticket
	 *
	 * @Param ticket id
	 */

	@Override
	public void cancelTicket(int tickeId) {
		ticketRepository.deleteById((long) tickeId);
	}

	/*
	 * Update Ticket Information
	 *
	 * @Param ticket id
	 * 
	 * @Param ticket object
	 *
	 * @returns ticket information
	 */
	@Override
	public Ticket updateTicket(int ticketId, Ticket ticket) {
		Ticket updateTicket = ticketRepository.findByTicketId(ticketId);
		if (!(ticket.getTicketId() == updateTicket.getTicketId()))
			updateTicket.setTicketId(ticket.getTicketId());

		if (!(ticket.getPassengerName().equalsIgnoreCase(updateTicket.getPassengerName())))
			updateTicket.setPassengerName(ticket.getPassengerName());

		if (!(ticket.getEmailAddr().equalsIgnoreCase(updateTicket.getEmailAddr())))
			updateTicket.setEmailAddr(ticket.getEmailAddr());

		if (!(ticket.getSrcStation().equalsIgnoreCase(updateTicket.getSrcStation())))
			updateTicket.setSrcStation(ticket.getSrcStation());

		if (!(ticket.getDestStation().equalsIgnoreCase(updateTicket.getDestStation())))
			updateTicket.setDestStation(ticket.getDestStation());

		if (!(ticket.getEmailAddr().equalsIgnoreCase(updateTicket.getEmailAddr())))
			updateTicket.setEmailAddr(ticket.getEmailAddr());

		if (!(ticket.getBookingDate().equals(updateTicket.getBookingDate())))
			updateTicket.setBookingDate(ticket.getBookingDate());

		return ticketRepository.save(updateTicket);
	}

}
