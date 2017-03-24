package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
 * @author anwender
 *
 */
@Named(value = "eventService")
@ApplicationScoped
public class EventService implements Serializable {

	private static final long serialVersionUID = 3420399168165290881L;

	@Inject
	private EntityManager entityManager;

	@PostConstruct
	public void initEventService() {
		if (getContentTables()) {
			User user = new User("Claudia", "Schaefers", new Date(System.currentTimeMillis()), "cs@jee.de", "123", true);
			User user1 = new User("a", "a", new Date(System.currentTimeMillis()), "a", "a", true);
			User user2 = new User("b", "b", new Date(System.currentTimeMillis()), "b", "b", false);
			
			Calendar today = Calendar.getInstance();
			today.set(2017, 0, 15);
			Event event1 = new Event("Test1", "Hallo ich bin die Beschreibung1", "Münster", today.getTime(), 1000,
					10.01, false, user1);
			Event event2 = new Event("Luke Mockridge: Lucky Man",
					"Lucky Luke – wer bei diesem Namen an einen einsamen Comic-Cowboy denkt, ist hier auf der falschen Fährte. „Komik-Cowboy” trifft es da wohl eher. Die Rede ist natürlich von Luke Mockridge, dem supertalentierten Comedian, der 2012 mit seinem Stand-up-Programm „Im lucky, I’m Luke” wie ein Komet in der deutschen Comedyszene einschlug und das Publikum seither mit seinem unwiderstehlichen Charme begeistert. Wo immer Luke auftritt, feiern die Leute ihn ab. Tausende haben bereits Tränen gelacht, wenn er auf der Bühne die Neunzigerjahre Revue passieren lässt, über seine Familie oder seine Ex plaudert und Alltagssituationen beschreibt, in denen sich jeder sofort wiederfindet. Nicht nur auf der Comedy-Bühne ist das charmante Energiebündel zu Hause, nein, Luke Mockridge hat viele Talente – er ist Moderator, Autor, Musiker, YouTuber und hat eine eigene Fernsehshow. Trotz seiner Riesenerfolge denkt der Sonnyboy nicht im Traum daran, sich darauf auszuruhen. Im Gegenteil, ein neues Programm hat er schon in der Pipeline: 2017 entert er mit „Lucky Man” erneut zahlreiche Locations deutschlandweit. Denn am allerliebsten bringt er sein Publikum live zum Lachen.",
					"München", today.getTime(), 5000, 20.00, true, user);
			
			List<Reservation> reservations = new ArrayList<Reservation>();
			Reservation reservation1 = new Reservation("ABC", 10, user, event1);
			reservations.add(reservation1);
			
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.persist(user1);
			entityManager.persist(user2);
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.persist(event1);
			entityManager.persist(event1.getUser());
			entityManager.persist(event2);
			entityManager.persist(event2.getUser());
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.persist(reservation1);
			entityManager.persist(reservation1.getEvent());
			entityManager.persist(reservation1.getUser());
			entityManager.getTransaction().commit();
		}
	}

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

	
	//TODO: wird garnicht verwendet oder??
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

	/**
	 * Überprüft, ob Inhalte in der Datenbank vorhanden sind und gibt falls dies der Fall ist false zurück.
	 * Ansonsten wird true zurückgegeben.
	 * @return
	 */
	public boolean getContentTables() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		try {
			List<User> users = query.getResultList();
			if (users.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}
	}

	//TODO: Neu erstellt, weil sonst ManagerService keine Liste bekommen hat
	/**
	 * Gibt alle Events aus der Datenbank zurück.
	 * @return
	 */
	public List<Event> getEvents(){
		List<Event> events = new ArrayList<Event>();

		TypedQuery<Event> query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
		try {
			events = query.getResultList();
			return events;
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
