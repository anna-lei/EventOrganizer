package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.EventService;
import de.auc.services.PageRenderingService;


@Named(value="homeBean")
@ApplicationScoped
public class HomeBean {
	private String searchText;
	private List<Event> events = new ArrayList<Event>();
	
	@Inject
	private EventService eventService;
	
	@Inject
	private PageRenderingService pageRenderingService;
	
	@PostConstruct
	public void initEvents() {
		events.clear();
		for(Event event: eventService.getEvents()) {
			events.add(event);
		}
	}
	
	public String search() { 
		events.clear();
		for(Event event: eventService.searchEvents(searchText)) {
			events.add(event);
		}
		return null;
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
	
	public String home() {
		return pageRenderingService.getHome();
	}


	
	
	
}
