package de.auc.services;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	
	public Reservation reserve(Event event, Integer selectedTickets) {
		eventService.getEventById(event.getEventid()).setNumberOfTickets(eventService.getEventById(event.getEventid()).getNumberOfTickets()-selectedTickets);
		//TODO Eventid
		Reservation reservation = new Reservation(5, generateCode(), selectedTickets, loginService.getActiveUser(), event);
		addReservation(reservation);
		return reservation;
		
	}
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	public String generateCode() {
		return UUID.randomUUID().toString();
		
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Reservation> getManagerReservations() {
		List<Reservation> managerReservations = new ArrayList<Reservation>();
		FacesMessage managerReservationMessage;
		for (Reservation reservation: reservations) {
			if(reservation.getEvent().getUser().equals(loginService.getActiveUser())) {
				managerReservations.add(reservation);
			}
		}
		if(managerReservations.size()==0){
			managerReservationMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Für den angemeldeten Manager wurden keine Reservierungen gefunden...", "");
			FacesContext.getCurrentInstance().addMessage(null, managerReservationMessage);
		} 
		return managerReservations;
	}
	
	
	

}
