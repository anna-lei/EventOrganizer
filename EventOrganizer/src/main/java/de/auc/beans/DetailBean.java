package de.auc.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.EventService;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@Named(value="detailBean")
@RequestScoped
public class DetailBean {

	private Event event;
	private Integer selectedTickets;
		
	@Inject
	private EventService eventService;
		
	@Inject
	private LoginService loginService;
	
	@Inject
	private PageRenderingService pageRenderingService;
	

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getSelectedTickets() {
		return selectedTickets;
	}

	public void setSelectedTickets(Integer selectedTickets) {
		this.selectedTickets = selectedTickets;
	}
	
	public String startReservation() {
		if(loginService.getActiveUser()==null){
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Für diese Operation müssen Sie angemeldet sein.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
			return pageRenderingService.getLogin();
		} else {
			System.out.println(this.event.toString());
			return pageRenderingService.getReservation();
		}
		
		
		
	}
	
	
	

	
	


	
	
	
	
}
