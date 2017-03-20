package de.auc.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.LoginService;
import de.auc.services.ManagerService;
import de.auc.services.PageRenderingService;

@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable{
	private String searchText;
	private Event event;
	private String filter;

	@Inject
	private ManagerService managerService;


	List<Event> managerEvents = new ArrayList<Event>();

	public String searchMyEvents() {
		managerEvents.clear();
		List<Event> currentEvents = managerService.searchManagerEvents(searchText, filter);
		if (currentEvents.size() == 0) {
			FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Für die Suche wurden keine Events gefunden...", "");
			FacesContext.getCurrentInstance().addMessage("search", searchMessage);
		} else {
			for (Event event : currentEvents) {
				managerEvents.add(event);
			}
			if (!searchText.isEmpty()) {
				FacesMessage searchMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Für den Suchbegriff \"" + searchText + "\" wurden folgende Events gefunden", "");
				FacesContext.getCurrentInstance().addMessage("search", searchMessage);
			}
		}
		return null;
	}

	@PostConstruct
	public void getMyEvents() {
		managerEvents.clear();
		for (Event event : managerService.getManagerEvents()) {
			managerEvents.add(event);
		}
	}

	public void publish() {
		managerService.publish(event);
		FacesMessage publishMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Das Event ist veröffentlicht und kann nicht mehr bearbeitet werden.", "");
		FacesContext.getCurrentInstance().addMessage("publishform:publish", publishMessage);
	}
	
	public String getPublicly(Event event){
		if(event.isPublicly()){
			return "Ja";
		} else{
			return "Nein";
		}
	}
	
	public String cancel() {
		return PageRenderingService.getMyEventDetail();
	}
	
	public String save() {
		managerService.saveEvent(event);
		return PageRenderingService.getMyEventDetail();
		
	}
	
	public String add() {
		if(FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			event = managerService.addEvent(event);
			managerEvents = managerService.getManagerEvents();
			return PageRenderingService.getMyEventDetail();
			
		} else {
			
			return PageRenderingService.getEditEvent();
		}
		
		
	}

	public List<Event> getManagerEvents() {
		return managerEvents;
	}

	public void setManagerEvents(List<Event> managerEvents) {
		this.managerEvents = managerEvents;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
