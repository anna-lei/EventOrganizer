package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.auc.model.Event;
import de.auc.services.EventService;


@ManagedBean
@ViewScoped
public class HomeBean {
	private String searchText;
	private List<Event> events = new ArrayList<Event>();
	
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	public String search() { 
		for(Event event: eventService.searchEvents(searchText)) {
			events.add(event);
		}
		return null;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	
	
	
}
