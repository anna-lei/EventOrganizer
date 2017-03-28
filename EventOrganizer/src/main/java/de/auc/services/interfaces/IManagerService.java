package de.auc.services.interfaces;

import java.util.List;

import de.auc.model.Event;

public interface IManagerService {

	/**
	 * Diese Methode gibt die Events des Managers zur�ck, der zu diesem Zeitpunkt angemeldet ist.
	 */
	List<Event> getManagerEvents();

	//TODO: SQL
	/**
	 * Diese Methode implementiert die Suche, die ein Manager in Bezug auf seine Events durchf�hren kann.
	 * @param searchText
	 * @param filter
	 * @return
	 */
	List<Event> searchManagerEvents(String searchText, String filter);

	/**
	 * Ver�nderung des Status eines Events innerhalb der Datenbank
	 */
	boolean publish(Event event);

	/**
	 * Speichern des Events nach Bearbeitung
	 */
	boolean saveEvent(Event event);

	/**
	 * Hinzuf�gen eines neuen Events in der Datenbank
	 */
	Event addEvent(Event event);

}