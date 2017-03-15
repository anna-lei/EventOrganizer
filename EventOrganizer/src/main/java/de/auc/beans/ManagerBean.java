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
import de.auc.model.User;
import de.auc.services.LoginService;
import de.auc.services.ManagerService;

@Named(value="managerBean")
@RequestScoped
public class ManagerBean {
		private Event event;
	
	@Inject
	private ManagerService managerService;
	@Inject
	private LoginService loginService;
	
	List<Event> managerEvents = new ArrayList<Event>();
	
	public List<Event> getMyEvents() {
		return managerService.getMyEvents(loginService.getActiveUser());

	}
	
	public void publish() {
		managerService.publish(event);
		FacesMessage publishMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Das Event ist veröffentlicht und kann nicht mehr bearbeitet werden.", "");
		FacesContext.getCurrentInstance().addMessage("publishform:publish", publishMessage);
		
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
	
	
	
	
}
