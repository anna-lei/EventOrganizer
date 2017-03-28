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
import de.auc.services.interfaces.IEventService;
import de.auc.services.interfaces.ILoginService;
import de.auc.services.interfaces.IManagerService;

/**
 * Der Managerservice dient der Implementierung zum Hinzufügen, Bearbeiten  und Veröffentlichung eines Events.
 *
 */
@Named(value = "managerService")
@RequestScoped
public class ManagerService implements Serializable, IManagerService{
	
	private static final long serialVersionUID = 2822982036287415573L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private IEventService eventService;
	
	@Inject
	private ILoginService loginService;

	
	@Override
	public List<Event> getManagerEvents() {
		List<Event> managerEvents = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user", Event.class);
		query.setParameter("user", loginService.getActiveUser());
		managerEvents = query.getResultList();
		return managerEvents;
		
	}

	
	@Override
	public List<Event> searchManagerEvents(String searchText, String filter) {
		List<Event> currentEvents = new ArrayList<Event>();

		
			if (filter == null) {
				TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user and e.name LIKE :search", Event.class);
				query.setParameter("search", "%" + searchText + "%");
				query.setParameter("user", loginService.getActiveUser());
				currentEvents = query.getResultList();
				return currentEvents;
						
				// Prüfung des Events auf den Suchtext und mitgegebenen Filter
				// in Bezug auf den Status der Veröffentlichung
			} else {
				TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e where e.user = :user and e.name LIKE :search and e.publicly = :filter", Event.class);
				query.setParameter("search", "%" + searchText + "%");
				query.setParameter("user", loginService.getActiveUser());
				query.setParameter("filter", Boolean.parseBoolean(filter));
				currentEvents = query.getResultList();
				return currentEvents;
				
			} 

	}

	
	@Override
	public boolean publish(Event event) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(event);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

	}

	
	@Override
	public boolean saveEvent(Event event) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(event);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
		
	}

	
	@Override
	public Event addEvent(Event event) {
		Event newEvent = new Event(event.getName(), event.getDescription(), event.getLocation(), event.getDate(), event.getNumberOfTickets(), event.getPrice(),  false,  loginService.getActiveUser());
		eventService.addEvent(newEvent);
		return newEvent;
	}

}
