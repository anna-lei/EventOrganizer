package de.auc.beans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.services.PageRenderingService;
import de.auc.services.interfaces.ILoginService;
import de.auc.services.interfaces.IReservationEventService;

/**
 * Diese Bean dient der Weiterleitung der Elemente bezogen auf die Reservierung an den Reservationservice.
 *
 */
@Named(value="reservationBean")
@ViewScoped
public class ReservationBean implements Serializable{
	
	private static final long serialVersionUID = -2689487395954330080L;
	private Event event;
	private Integer selectedTickets = 2;
	private Reservation reservation;
	private List<Reservation> managerReservations = new ArrayList<Reservation>();

		
	
	@Inject
	private IReservationEventService reservationEventService;
	
	@Inject
	private ILoginService loginService;
	
	/**
	 * Diese Methode wird durch den Button "Reservieren" aufgerufen 
	 * und die Reservierung nach Prüfung an den Service weitergegeben.
	 */
	public String reserve() {
		// Eine Reservierung ist nur nach einer Anmeldung möglich
		if(loginService.getActiveUser()==null) {
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sie müssen für diese Operation angemeldet sein.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
			return PageRenderingService.getLogin();
		//Hier wird geprüft, ob die angegebene Menge an Tickets nicht größer ist als die Anzahl an verfügbaren Tickets
		} else if (event.getNumberOfTickets()<selectedTickets){
			FacesMessage reservationMessage = 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es sind nur " + event.getNumberOfTickets() + " Tickets verfügbar.", "");
			FacesContext.getCurrentInstance().addMessage("reservationForm:reservation", reservationMessage);
			return null;
		}else{
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(2);
			nf.setMaximumFractionDigits(2);
			String sumPrice = nf.format(selectedTickets*event.getPrice());
			
			reservation = reservationEventService.reserve(event, selectedTickets);
			//Message nach erfolgreicher Reservierung
			FacesMessage reservationMessage = 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Vielen Dank, " + loginService.getActiveUser().getPrename() +"! Folgende Tickets wurden erfolgreich mit einem Gesamtpreis von " + sumPrice + "€ für Sie reserviert.", "");
			FacesContext.getCurrentInstance().addMessage("reservation", reservationMessage);
			return null;
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
