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
import javax.transaction.Transactional;

import de.auc.model.Event;

/**
 * Der Managerservice dient der Implementierung zum Hinzuf�gen, Bearbeiten  und Ver�ffentlichung eines Events.
 *
 */
@Named(value = "managerService")
@RequestScoped
public class ManagerService implements Serializable{
	
	private static final long serialVersionUID = 2822982036287415573L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;

	/**
	 * Diese Methode gibt die Events des Managers zur�ck, der zu diesem Zeitpunkt angemeldet ist.
	 */
	public List<Event> getManagerEvents() {
		List<Event> managerEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user", Event.class);
		query.setParameter("user", loginService.getActiveUser());
		
		try {
			managerEvents = query.getResultList();
			return managerEvents;
		} catch (NoResultException e) {
			return null;
		}
		
	}

	//TODO: SQL
	/**
	 * Diese Methode implementiert die Suche, die ein Manager in Bezug auf seine Events durchf�hren kann.
	 * @param searchText
	 * @param filter
	 * @return
	 */
	public List<Event> searchManagerEvents(String searchText, String filter) {
		List<Event> currentEvents = new ArrayList<Event>();

		
			if (filter == null) {
				TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user and e.name LIKE :search", Event.class);
				query.setParameter("search", "%" + searchText + "%");
				query.setParameter("user", loginService.getActiveUser());
				try {
					currentEvents = query.getResultList();
					return currentEvents;
				} catch (NoResultException e) {
					return null;
				}
			
				
				// Pr�fung des Events auf den Suchtext und mitgegebenen Filter
				// in Bezug auf den Status der Ver�ffentlichung
			} else {
				TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user and e.name LIKE :search and e.publicly = :filter", Event.class);
				query.setParameter("search", "%" + searchText + "%");
				query.setParameter("user", loginService.getActiveUser());
				query.setParameter("filter", Boolean.parseBoolean(filter));
				try {
					currentEvents = query.getResultList();
					return currentEvents;
				} catch (NoResultException e) {
					return null;
				}
			} 

	}

	/**
	 * Ver�nderung des Status eines Events innerhalb der Datenbank
	 */
	public void publish(Event event) {
		entityManager.getTransaction().begin();
		entityManager.merge(event);
		entityManager.getTransaction().commit();

	}

	/**
	 * Speichern des Events nach Bearbeitung
	 */
	public void saveEvent(Event event) {
		entityManager.getTransaction().begin();
		entityManager.merge(event);
		entityManager.getTransaction().commit();
		
	}

	/**
	 * Hinzuf�gen eines neuen Events in der Datenbank
	 */
	public Event addEvent(Event event) {
		Event newEvent = new Event(event.getName(), event.getDescription(), event.getLocation(), event.getDate(), event.getNumberOfTickets(), event.getPrice(),  false,  loginService.getActiveUser());
		eventService.addEvent(newEvent);
		return newEvent;
	}

}
