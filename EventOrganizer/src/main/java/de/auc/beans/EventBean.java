package de.auc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.interfaces.IEventService;

/**
 * Diese Bean ist zugeh�rig zur Startseite 
 * und bef�llt beispielsweise die Liste der aktuellen Events
 * sowie die Implementierung der Suche auf der Startseite.
 *
 */
@Named(value = "eventBean")
@RequestScoped
public class EventBean implements Serializable {
	
	private static final long serialVersionUID = 5568201321204202019L;
	private String searchText;
	private List<Event> events = new ArrayList<Event>();
	
	@Inject
	private IEventService eventService;

	/**
	 * Da die Bean RequestScoped ist, wird initial durch das PostConstruct die Liste der aktuellen Events gef�llt.
	 */
	@PostConstruct
	public void initEvents() {
		events.clear();
		List<Event> publiclyEvents = eventService.getPubliclyEvents();
		for (Event event : publiclyEvents) {
			events.add(event);
		}
	}

	/**
	 * Implementierung der Suchfunktion �ber den Eventservice,
	 * jedoch wird hier die jeweilige Facesmessage f�r den Suchbegriff erstellt.
	 */
	public void search() {
		events.clear();
		List<Event> currentEvents = eventService.searchEvents(searchText);
		if (currentEvents.size() == 0) {
			FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"F�r den Suchbegriff wurden keine Events gefunden...", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
		} else {
			for (Event event : currentEvents) {
				events.add(event);
			}
			if (!searchText.isEmpty()) {
				FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"F�r den Suchbegriff \"" + searchText + "\" wurden folgende Events gefunden", "");
				FacesContext.getCurrentInstance().addMessage("search", searchMessage);
			}
		}

	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
