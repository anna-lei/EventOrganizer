package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.model.User;

/**
 * Eventservice, der alle Services in Bezug auf das Event enthält.
 * Suche nach Events, hinzufügen, bearbeiten usw.
 */
@Named(value = "eventService")
@ApplicationScoped
public class EventService implements Serializable {

	private static final long serialVersionUID = 3420399168165290881L;

	@Inject
	private EntityManager entityManager;


	/**
	 * Suchfunktion der Anwendung
	 * Hierbei werden lediglich die Titel der veröffentlichten Events nach dem eingegebenen Suchbegriff durchsucht.
	 * @param searchText
	 * @return
	 */
	public List<Event> searchEvents(String searchText) {
		List<Event> currentEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.publicly=true and e.name LIKE :search", Event.class);
		query.setParameter("search", "%" + searchText + "%");
		try {
			currentEvents = query.getResultList();
			return currentEvents;
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Zeigt alle veröffentlichten Events an.
	 * @return
	 */
	public List<Event> getPubliclyEvents() {
		List<Event> currentEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.publicly=true", Event.class);
		try {
			currentEvents = query.getResultList();
			return currentEvents;
		} catch (NoResultException e) {
			return null;
		}
	}

	
	/**
	 * Fügt eine Event zur Datenbank hinzu.
	 * @param event
	 */
	@Transactional
	public void addEvent(Event event){
		entityManager.getTransaction().begin();
		entityManager.persist(event);
		entityManager.getTransaction().commit();
	}

	/**
	 * Gibt das Event zum mitgegebenen Namen zurück.
	 * @param name
	 * @return
	 */
	public Event getEventByName(String name) {
		Event event;
		
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.name = :name", Event.class);
		query.setParameter("name", name);
		try {
			event = query.getSingleResult();
			return event;
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Gibt das Event zu der mitgegebenen eventid zurück.
	 * @param eventid
	 * @return
	 */
	public Event getEventById(Integer eventid) {
		Event event;
		
		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.eventid = :id", Event.class);
		query.setParameter("id", eventid);
		try {
			event = query.getSingleResult();
			return event;
		} catch (NoResultException e) {
			return null;
		}
	}

	
	
}
