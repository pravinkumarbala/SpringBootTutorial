package com.app.service;

import com.app.dao.Ticket;

public interface TicketService {

    public Iterable<Ticket> fetchTicketDB();
    public Ticket createTicket(Ticket ticket);
    public Ticket fetchTicketDetail(int ticketId);
    public void cancelTicket(int tickeId);
    public Ticket updateTicket(int ticketId, Ticket ticket);

}