package de.auc.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;

@Named(value="eventService")
@ApplicationScoped
public class EventService {
	private List<Event> events = new ArrayList<Event>();
	
	@Inject
	private UserService userService;

	@PostConstruct
	public void initEventService() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Reservation> reservations1 = new ArrayList<Reservation>();
		Event event1 = new Event(1, "Test1", "Hallo ich bin die Beschreibung1", "M�nster", "25.07.2017 19 Uhr", 1000, true, reservations, userService.getUserByName("a"));
		Event event2 = new Event(2, "Test2", "Hallo ich bin die Beschreibung2", "M�nchen", "18.08.2017 19 Uhr", 5000, false, reservations1, userService.getUserByName("a"));
		this.addEvent(event1);
		this.addEvent(event2);
		
	}
	
	public List<Event> searchEvents(String searchText) {
		List<Event> currentEvents = new ArrayList<Event>();
		
		for (Event event: events) {
			if(event.getName().toLowerCase().contains(searchText.toLowerCase())) {
				currentEvents.add(event);
			}
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
			if(event.getEventid().equals(eventid)){
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
