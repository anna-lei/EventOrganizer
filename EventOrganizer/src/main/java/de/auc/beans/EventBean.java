package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.EventService;
import de.auc.services.PageRenderingService;


@Named(value="eventBean")
@ApplicationScoped
public class EventBean {
	private String searchText;
	private List<Event> events = new ArrayList<Event>();
	
	@Inject
	private EventService eventService;
	
	
	@PostConstruct
	public void initEvents() {
		events.clear();
		for(Event event: eventService.getEvents()) {
			events.add(event);
		}
	}
	
	public void search() { 
		events.clear();
		if(eventService.searchEvents(searchText).size()==0) {
			FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Für den Suchbegriff wurden keine Events gefunden...", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
		} else {
			
			for(Event event: eventService.searchEvents(searchText)) {
				events.add(event);
			}
			FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Für den Suchbegriff \"" + searchText + "\" wurden folgende Events gefunden", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
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
