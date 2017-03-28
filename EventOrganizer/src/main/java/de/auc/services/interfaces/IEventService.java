package de.auc.services.interfaces;

import java.util.List;

import javax.transaction.Transactional;

import de.auc.model.Event;

public interface IEventService {

	/**
	 * Suchfunktion der Anwendung
	 * Hierbei werden lediglich die Titel der veröffentlichten Events nach dem eingegebenen Suchbegriff durchsucht.
	 * @param searchText
	 * @return
	 */
	List<Event> searchEvents(String searchText);

	/**
	 * Zeigt alle veröffentlichten Events an.
	 * @return
	 */
	List<Event> getPubliclyEvents();

	/**
	 * Fügt eine Event zur Datenbank hinzu.
	 * @param event
	 */
	void addEvent(Event event);

	/**
	 * Gibt das Event zum mitgegebenen Namen zurück.
	 * @param name
	 * @return
	 */
	Event getEventByName(String name);

	/**
	 * Gibt das Event zu der mitgegebenen eventid zurück.
	 * @param eventid
	 * @return
	 */
	Event getEventById(Integer eventid);

}