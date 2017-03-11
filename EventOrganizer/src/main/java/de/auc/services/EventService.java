package de.auc.services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.auc.model.Event;
import de.auc.model.User;

@ManagedBean
@ApplicationScoped
public class EventService {
	private List<Event> events = new ArrayList<Event>();
	private Event activeEvent;

	public EventService() {
		Event event1 = new Event("Test1", "Hallo ich bin die Beschreibung1", "Münster", "25.07.2017 19 Uhr", 1000, true);
		Event event2 = new Event("Test2", "Hallo ich bin die Beschreibung2", "München", "18.08.2017 19 Uhr", 5000, false);
		this.addEvent(event1);
		this.addEvent(event2);
		
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	public Event getEvent(String name) {
		for(Event event: events) {
			if(event.getName().equals(name)){
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

	public Event getActiveEvent() {
		return activeEvent;
	}

	public void setActiveEvent(Event activeEvent) {
		this.activeEvent = activeEvent;
	}
	
	
	
}
