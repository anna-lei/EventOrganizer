package de.auc.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.LoginService;
import de.auc.services.ManagerService;

@Named(value = "managerBean")
@RequestScoped
public class ManagerBean {
	private String searchText;
	private Event event;
	private String filter;

	@Inject
	private ManagerService managerService;
	@Inject
	private LoginService loginService;

	List<Event> managerEvents = new ArrayList<Event>();

	public String searchMyEvents() {
		managerEvents.clear();
		List<Event> currentEvents = managerService.searchManagerEvents(loginService.getActiveUser(), searchText, filter);
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
		for (Event event : managerService.getManagerEvents(loginService.getActiveUser())) {
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
	
	public String save() {
		return null;
		
	}
	
	public String add() {
		return null;
		
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
