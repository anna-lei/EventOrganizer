package de.auc.services;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;

@Named(value="reservationEventService")
@RequestScoped
public class ReservationEventService implements Serializable{
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;
	
	
	public void reserve(Event event, Integer selectedTickets) {
		eventService.getEventById(event.getEventid()).setNumberOfTickets(eventService.getEventById(event.getEventid()).getNumberOfTickets()-selectedTickets);
		//TODO Eventid
		this.addReservation(new Reservation(5, generateCode(), selectedTickets, loginService.getActiveUser(), event)); 
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	public String generateCode() {
		//TODO generierung implementieren
		return "123456";
		
	}
	
	

}
