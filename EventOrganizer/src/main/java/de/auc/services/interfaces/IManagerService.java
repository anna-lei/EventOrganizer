package de.auc.services.interfaces;

import java.util.List;

import de.auc.model.Event;

public interface IManagerService {

	
	List<Event> getManagerEvents();

	List<Event> searchManagerEvents(String searchText, String filter);

	boolean publish(Event event);

	boolean saveEvent(Event event);

	Event addEvent(Event event);

}