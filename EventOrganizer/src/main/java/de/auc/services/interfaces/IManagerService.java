package de.auc.services.interfaces;

import java.util.List;

import de.auc.model.Event;

public interface IManagerService {

	/**
	 * Diese Methode gibt die Events des Managers zurück, der zu diesem Zeitpunkt angemeldet ist.
	 */
	List<Event> getManagerEvents();

	//TODO: SQL
	/**
	 * Diese Methode implementiert die Suche, die ein Manager in Bezug auf seine Events durchführen kann.
	 * @param searchText
	 * @param filter
	 * @return
	 */
	List<Event> searchManagerEvents(String searchText, String filter);

	/**
	 * Veränderung des Status eines Events innerhalb der Datenbank
	 */
	boolean publish(Event event);

	/**
	 * Speichern des Events nach Bearbeitung
	 */
	boolean saveEvent(Event event);

	/**
	 * Hinzufügen eines neuen Events in der Datenbank
	 */
	Event addEvent(Event event);

}