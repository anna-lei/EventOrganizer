package de.auc.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.PageRenderingService;
import de.auc.services.ReservationEventService;

@Named(value="reservationBean")
@RequestScoped
public class ReservationBean {
	private Event event;
	private Integer selectedTickets;
		
	@Inject
	private PageRenderingService pageRenderingService;
	
	@Inject
	private ReservationEventService reservationEventService;
	
	public String reserve() {
		reservationEventService.reserve(event, selectedTickets);
		return pageRenderingService.getReservation();
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

	
}