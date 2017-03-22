package de.auc.services;


import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;

@Named(value="reservationEventService")
@RequestScoped
public class ReservationEventService implements Serializable{
	
	
	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;
	
	
	
	
	public Reservation reserve(Event event, Integer selectedTickets) {

		eventService.getEventById(event.getEventid()).setNumberOfTickets(eventService.getEventById(event.getEventid()).getNumberOfTickets()-selectedTickets);
		//TODO Reservierungsid
		Reservation reservation = new Reservation(generateCode(), selectedTickets, loginService.getActiveUser(), event);
		//TODO Mapping zwischen Event und Reservierung
		event.getReservations().add(reservation);
		
		return reservation;
		
	}
	
	
	public String generateCode() {
		return UUID.randomUUID().toString();
		
	}


	
	
	

}
