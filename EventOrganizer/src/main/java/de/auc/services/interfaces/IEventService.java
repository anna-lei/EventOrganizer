package de.auc.services.interfaces;

import java.util.List;

import javax.transaction.Transactional;

import de.auc.model.Event;

public interface IEventService {

	/**
	 * Suchfunktion der Anwendung
	 * Hierbei werden lediglich die Titel der ver�ffentlichten Events nach dem eingegebenen Suchbegriff durchsucht.
	 * @param searchText
	 * @return
	 */
	List<Event> searchEvents(String searchText);

	/**
	 * Zeigt alle ver�ffentlichten Events an.
	 * @return
	 */
	List<Event> getPubliclyEvents();

	/**
	 * F�gt eine Event zur Datenbank hinzu.
	 * @param event
	 */
	void addEvent(Event event);

	/**
	 * Gibt das Event zum mitgegebenen Namen zur�ck.
	 * @param name
	 * @return
	 */
	Event getEventByName(String name);

	/**
	 * Gibt das Event zu der mitgegebenen eventid zur�ck.
	 * @param eventid
	 * @return
	 */
	Event getEventById(Integer eventid);

}