package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;
import de.auc.services.ReservationEventService;

@Named(value="reservationBean")
@RequestScoped
public class ReservationBean {
	private Event event;
	private Integer selectedTickets = 2;
	private Reservation reservation;
	private List<Reservation> reservations = new ArrayList<Reservation>();
		
	
	@Inject
	private ReservationEventService reservationEventService;
	
	@Inject
	private LoginService loginService;
		
	public String reserve() {
		if(loginService.getActiveUser()==null) {
			return PageRenderingService.getLogin();
		} else {
			reservation = reservationEventService.reserve(event, selectedTickets);
			return null;
		}
		
		
		
	}
	
	public String getManagerReservations() { 
		reservations.clear();
		for(Reservation reservation: reservationEventService.getManagerReservations()) {
			reservations.add(reservation);
		}
		return null;
	}
	
	

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getSelectedTickets() {
		return selectedTickets;
	}

	public void setSelectedTickets(Integer selectedTickets) {
		this.selectedTickets = selectedTickets;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
	
}
