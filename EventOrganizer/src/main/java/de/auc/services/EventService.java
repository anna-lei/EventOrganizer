package de.auc.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.auc.model.Event;

@Named(value="eventService")
@ApplicationScoped
public class EventService {
	private List<Event> events = new ArrayList<Event>();

	public EventService() {
		Event event1 = new Event(1, "Test1", "Hallo ich bin die Beschreibung1", "Münster", "25.07.2017 19 Uhr", 1000, true);
		Event event2 = new Event(2, "Test2", "Hallo ich bin die Beschreibung2", "München", "18.08.2017 19 Uhr", 5000, false);
		this.addEvent(event1);
		this.addEvent(event2);
		
	}
	
	public List<Event> searchEvents(String searchText) {
		List<Event> currentEvents = new ArrayList<Event>();
		FacesMessage searchMessage;
		for (Event event: events) {
			if(event.getName().toLowerCase().contains(searchText.toLowerCase())) {
				currentEvents.add(event);
			}
		}
		if(currentEvents.size()==0){
			searchMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Für den Suchbegriff wurden keine Events gefunden...", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
		} else {
			searchMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Für den Suchbegriff \"" + searchText + "\" wurden folgende Events gefunden", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
		}
		return currentEvents;
		
		
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	public Event getEventByName(String name) {
		for(Event event: events) {
			if(event.getName().equals(name)){
				return event;
			}
		}
		return null;
	}
	
	public Event getEventById(Integer eventid) {
		for(Event event: events) {
			System.out.println("get event by id");
			if(event.getEventid().equals(eventid)){
				System.out.println("gefunden");
				return event;
			}
		}
		return null;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	

	
	
	
	
}
