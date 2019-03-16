package com.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.dao.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{
     public Ticket findByTicketId(int ticketId);
}