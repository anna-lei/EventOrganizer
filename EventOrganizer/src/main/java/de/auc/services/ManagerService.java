package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.User;

@Named(value = "managerService")
@SessionScoped
public class ManagerService implements Serializable {

	@Inject
	private EventService eventService;

	public List<Event> getManagerEvents(User manager) {
		List<Event> managerEvents = new ArrayList<Event>();
		for (Event event : eventService.getEvents()) {
			if (event.getUser().getMail().equals(manager.getMail())) {
				managerEvents.add(event);
			}
		}
		return managerEvents;
	}

	public List<Event> searchManagerEvents(User manager, String searchText, String filter) {
		List<Event> currentEvents = new ArrayList<Event>();

		for (Event event : eventService.getEvents()) {
			if (event.getUser().getMail().equals(manager.getMail())) {
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

}
