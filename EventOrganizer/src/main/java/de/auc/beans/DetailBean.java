package de.auc.beans;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.EventService;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@Named(value="detailBean")
@RequestScoped
public class DetailBean {

	private Event event;
	private Integer selectedTickets = 2;
		
	@Inject
	private EventService eventService;
		
	@Inject
	private LoginService loginService;
	
	@Inject
	private PageRenderingService pageRenderingService;
	

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
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
