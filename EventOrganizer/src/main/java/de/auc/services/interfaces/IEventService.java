package de.auc.services.interfaces;

import java.util.List;

import de.auc.model.Event;

public interface IEventService {

	List<Event> searchEvents(String searchText);

	List<Event> getPubliclyEvents();

	void addEvent(Event event);

	Event getEventByName(String name);

	Event getEventById(Integer eventid);

}