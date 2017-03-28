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
import de.auc.services.interfaces.IEventService;

/**
 * Eventservice, der alle Services in Bezug auf das Event enthält.
 * Suche nach Events, hinzufügen, bearbeiten usw.
 */
@Named(value = "eventService")
@ApplicationScoped
public class EventService implements Serializable, IEventService {

	private static final long serialVersionUID = 3420399168165290881L;

	@Inject
	private EntityManager entityManager;


	/**
	 * Suchfunktion der Anwendung
	 * Hierbei werden lediglich die Titel der veröffentlichten Events nach dem eingegebenen Suchbegriff durchsucht.
	 * @param searchText
	 * @return
	 */
	@Override
	public List<Event> searchEvents(String searchText) {
		List<Event> currentEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.publicly=true and e.name LIKE :search", Event.class);
		query.setParameter("search", "%" + searchText + "%");
		currentEvents = query.getResultList();
		return currentEvents;
		
	}


	/**
	 * Zeigt alle veröffentlichten Events an.
	 * @return
	 */
	@Override
	public List<Event> getPubliclyEvents() {
		List<Event> currentEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.publicly=true", Event.class);
		currentEvents = query.getResultList();
		return currentEvents;
		
	}

	
	/**
	 * Fügt eine Event zur Datenbank hinzu.
	 * @param event
	 */
	@Override
	@Transactional
	public void addEvent(Event event){
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(event);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	/**
	 * Gibt das Event zum mitgegebenen Namen zurück.
	 * @param name
	 * @return
	 */
	@Override
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
	@Override
	public Event getEventById(Integer eventid) {
		return entityManager.find(Event.class, eventid);
	}

	
	
}
