package de.auc.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.model.Reservation;

@Named(value="eventService")
@ApplicationScoped
public class EventService {
	private List<Event> events = new ArrayList<Event>();
	
	@Inject
	private UserService userService;

	@PostConstruct
	public void initEventService() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Reservation> reservations1 = new ArrayList<Reservation>();
		Event event1 = new Event(1, "Test1", "Hallo ich bin die Beschreibung1", "Münster", "25.07.2017 19 Uhr", 1000,10,  true,  reservations, userService.getUserByName("a"));
		Event event2 = new Event(2, "Luke Mockridge: Lucky Man", "Lucky Luke – wer bei diesem Namen an einen einsamen Comic-Cowboy denkt, ist hier auf der falschen Fährte. „Komik-Cowboy” trifft es da wohl eher. Die Rede ist natürlich von Luke Mockridge, dem supertalentierten Comedian, der 2012 mit seinem Stand-up-Programm „Im lucky, I’m Luke” wie ein Komet in der deutschen Comedyszene einschlug und das Publikum seither mit seinem unwiderstehlichen Charme begeistert. Wo immer Luke auftritt, feiern die Leute ihn ab. Tausende haben bereits Tränen gelacht, wenn er auf der Bühne die Neunzigerjahre Revue passieren lässt, über seine Familie oder seine Ex plaudert und Alltagssituationen beschreibt, in denen sich jeder sofort wiederfindet. Nicht nur auf der Comedy-Bühne ist das charmante Energiebündel zu Hause, nein, Luke Mockridge hat viele Talente – er ist Moderator, Autor, Musiker, YouTuber und hat eine eigene Fernsehshow. Trotz seiner Riesenerfolge denkt der Sonnyboy nicht im Traum daran, sich darauf auszuruhen. Im Gegenteil, ein neues Programm hat er schon in der Pipeline: 2017 entert er mit „Lucky Man” erneut zahlreiche Locations deutschlandweit. Denn am allerliebsten bringt er sein Publikum live zum Lachen.", "München", "18.08.2017 19 Uhr", 5000, 20,  false, reservations1, userService.getUserByName("cs@jee.de"));
		this.addEvent(event1);
		this.addEvent(event2);
		
	}
	
	public List<Event> searchEvents(String searchText) {
		List<Event> currentEvents = new ArrayList<Event>();
		
		for (Event event: events) {
			if(event.getName().toLowerCase().contains(searchText.toLowerCase())) {
				currentEvents.add(event);
			}
		}
		
		return currentEvents;
		
		
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	public Event getEventByName(String name) {
		for(Event event: events) {
			if(event.getName().equals(name)){
				return event;
			}
		}
		return null;
	}
	
	public Event getEventById(Integer eventid) {
		for(Event event: events) {
			if(event.getEventid().equals(eventid)){
				return event;
			}
		}
		return null;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	

	
	
	
	
}
