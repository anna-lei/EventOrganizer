package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.model.User;

@Named(value = "managerService")
@SessionScoped
public class ManagerService implements Serializable{

	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;

	public List<Event> getManagerEvents() {
		List<Event> managerEvents = new ArrayList<Event>();
		for (Event event : eventService.getEvents()) {
			if (event.getUser().getMail().equals(loginService.getActiveUser().getMail())) {
				managerEvents.add(event);
			}
		}
		return managerEvents;
	}

	public List<Event> searchManagerEvents(String searchText, String filter) {
		List<Event> currentEvents = new ArrayList<Event>();

		for (Event event : eventService.getEvents()) {
			if (event.getUser().getMail().equals(loginService.getActiveUser().getMail())) {
				if (filter == null) {
					if (event.getName().toLowerCase().contains(searchText.toLowerCase())) {
						currentEvents.add(event);
					}
				} else if (event.getName().toLowerCase().contains(searchText.toLowerCase())
						&& event.isPublicly() == Boolean.parseBoolean(filter)) {
					currentEvents.add(event);
				}
			}
		}
		return currentEvents;
	}

	public void publish(Event event) {
		event.setPublicly(true);
	}

	public void saveEvent(Event event) {
		Event currentEvent = eventService.getEventById(event.getEventid());
		currentEvent.setPrice(event.getPrice());
		currentEvent.setDate(event.getDate());
		currentEvent.setDescription(event.getDescription());
		currentEvent.setLocation(event.getLocation());
		currentEvent.setName(event.getName());
		currentEvent.setNumberOfTickets(event.getNumberOfTickets());
		
	}

	public Event addEvent(Event event) {
		//TODO Id
		Event newEvent = new Event(100, event.getName(), event.getDescription(), event.getLocation(), event.getDate(), event.getNumberOfTickets(), event.getPrice(),  false,  new ArrayList<Reservation>(), loginService.getActiveUser());
		eventService.addEvent(newEvent);
		return newEvent;
		
	}

}
