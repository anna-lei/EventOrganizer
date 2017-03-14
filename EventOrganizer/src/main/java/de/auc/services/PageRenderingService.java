package de.auc.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;

@Named(value="pageRenderingService")
@ApplicationScoped
public class PageRenderingService {
	
	private static final String PAGE_EVENT_DETAIL_JSF = "eventDetail.jsf";
	private static final String PAGE_REGISTER_JSF = "register.jsf";
	private static final String PAGE_LOGIN_JSF = "login.jsf";
	private static final String PAGE_HOME_JSF = "home.jsf";
	private static final String PAGE_MYEVENTS_JSF = "myEvents.jsf";
	private static final String PAGE_MANAGER_RESERVATION_JSF = "managerReservierung.jsf";
	private static final String PAGE_MANAGER_DETAIL_JSF = "managerDetail.jsf";
	
	@Inject
	private EventService eventService;

	public String getManagerDetail(){
		return PAGE_MANAGER_DETAIL_JSF;
	}
	
	public String getManagerReservation(){
		return PAGE_MANAGER_RESERVATION_JSF;
	}
	
	public String getMyEvents() {
		return PAGE_MYEVENTS_JSF;
	}
	
	public String getHome() {
		return PAGE_HOME_JSF;
	}
	
	public String getLogin() {
		return PAGE_LOGIN_JSF;
	}
	
	public String getRegister() {
		return PAGE_REGISTER_JSF;
	}
	
	public String getEventDetail(Event event) {
		return PAGE_EVENT_DETAIL_JSF;
		
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	
}
