package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.Ticket;
import com.app.service.TicketService;

@RestController
@RequestMapping(value = "/api/ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping(value = "/list")
	public Iterable<Ticket> ticketDB() {
		return ticketService.fetchTicketDB();
	}

	@PostMapping(value = "/book")
	public Ticket bookTicket(@RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}

	@GetMapping(value = "/ticketInfo/{ticketId}")
	public Ticket getTicketDetail(@PathVariable("ticketId") int ticketId) {
		return ticketService.fetchTicketDetail(ticketId);
	}

	@DeleteMapping(value = "/cancel/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId") int ticketId) {
		ticketService.cancelTicket(ticketId);
	}

	@PutMapping(value = "/update/{ticketId}")
	public Ticket updateTicket(@PathVariable("ticketId") int ticketId, @RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticketId, ticket);
	}
}