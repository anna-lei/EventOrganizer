package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	private List<Reservation> managerReservations = new ArrayList<Reservation>();

		
	
	@Inject
	private ReservationEventService reservationEventService;
	
	@Inject
	private LoginService loginService;
		
	public String reserve() {
		if(loginService.getActiveUser()==null) {
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sie müssen für diese Operation angemeldet sein.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
			return PageRenderingService.getLogin();
		} else {
			reservation = reservationEventService.reserve(event, selectedTickets);
			FacesMessage reservationMessage = 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Vielen Dank, " + loginService.getActiveUser().getPrename() +"! Folgende Tickets wurden erfolgreich mit einem Gesamtpreis von " + selectedTickets*event.getPrice() + "€ für Sie reserviert.", "");
			FacesContext.getCurrentInstance().addMessage("reservation", reservationMessage);
			return PageRenderingService.getReservation();
		}
		
		
		
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

	public List<Reservation> getManagerReservations() {
		return managerReservations;
	}

	public void setManagerReservations(List<Reservation> managerReservations) {
		this.managerReservations = managerReservations;
	}

	
	



	
	
}
