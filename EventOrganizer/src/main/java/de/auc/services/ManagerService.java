package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import de.auc.model.Event;
import de.auc.model.User;

/**
 * Der Managerservice dient der Implementierung zum Hinzufügen, Bearbeiten  und Veröffentlichung eines Events.
 *
 */
@Named(value = "managerService")
@RequestScoped
public class ManagerService implements Serializable{
	
	private static final long serialVersionUID = 2822982036287415573L;

	
	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;

	/**
	 * Diese Methode gibt die Events des Managers zurück, der zu diesem Zeitpunkt angemeldet ist.
	 */
	//TODO kein SQL?
	public List<Event> getManagerEvents() {
		List<Event> managerEvents = new ArrayList<Event>();
		for (Event event : eventService.getEvents()) {
			if (event.getUser().getMail().equals(loginService.getActiveUser().getMail())) {
				managerEvents.add(event);
			}
		}
		return managerEvents;
	}

	/**
	 * Diese Methode implementiert die Suche, die ein Manager in Bezug auf seine Events durchführen kann.
	 * @param searchText
	 * @param filter
	 * @return
	 */
	//TODO: Muss das überarbeitet werden??
	public List<Event> searchManagerEvents(String searchText, String filter) {
		List<Event> currentEvents = new ArrayList<Event>();

		for (Event event : eventService.getEvents()) {
			//Prüfung des Events, ob dieses dem Manager zugehörig ist
			if (event.getUser().getMail().equals(loginService.getActiveUser().getMail())) {
				if (filter == null) {
					if (event.getName().toLowerCase().contains(searchText.toLowerCase())) {
						currentEvents.add(event);
					}
				//Prüfung des Events auf den Suchtext und mitgegebenen Filter in Bezug auf den Status der Veröffentlichung
				} else if (event.getName().toLowerCase().contains(searchText.toLowerCase())
						&& event.isPublicly() == Boolean.parseBoolean(filter)) {
					currentEvents.add(event);
				}
			}
		}
		return currentEvents;
	}

	/**
	 * Veränderung des Status eines Events innerhalb der Datenbank
	 */
	//TODO SQL -> richtig
	public void publish(Event event) {
		event.setPublicly(true);
//		TypedQuery<Event> query = entityManager.createQuery("UPDATE Event e SET e.publicly = true WHERE e.eventid =:id", Event.class);
//		query.setParameter("id", event.getEventid());
	}

	/**
	 * Speichern des Events nach Bearbeitung
	 */
	//TODO SQL
	public void saveEvent(Event event) {
		Event currentEvent = eventService.getEventById(event.getEventid());
		currentEvent.setPrice(event.getPrice());
		currentEvent.setDate(event.getDate());
		currentEvent.setDescription(event.getDescription());
		currentEvent.setLocation(event.getLocation());
		currentEvent.setName(event.getName());
		currentEvent.setNumberOfTickets(event.getNumberOfTickets());
		
	}

	/**
	 * Hinzufügen eines neuen Events in der Datenbank
	 */
	public Event addEvent(Event event) {
		Event newEvent = new Event(event.getName(), event.getDescription(), event.getLocation(), event.getDate(), event.getNumberOfTickets(), event.getPrice(),  false,  loginService.getActiveUser());
		eventService.addEvent(newEvent);
		return newEvent;
	}

}
