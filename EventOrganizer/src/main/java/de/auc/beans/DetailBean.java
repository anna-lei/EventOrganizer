package de.auc.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.auc.model.Event;
import de.auc.services.EventService;

@ManagedBean
@ViewScoped
public class DetailBean {

	private Event event;
	
	@ManagedProperty("#{eventService}")
	private EventService eventService;

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
	
	
	
	
}
