package de.auc.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.EventService;

@Named(value="detailBean")
@RequestScoped
public class DetailBean {

	private Event event;
	
	@Inject
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
