package de.auc.services;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="pageRenderingService")
@ApplicationScoped
public class PageRenderingService implements Serializable{
	
	
	private static final String PAGE_REGISTER_JSF = "register.jsf";
	private static final String PAGE_LOGIN_JSF = "login.jsf";
	private static final String PAGE_HOME_JSF = "home.jsf";
	private static final String PAGE_MYEVENTS_JSF = "myEvents.jsf";
	private static final String PAGE_MANAGER_RESERVATION_JSF = "managerReservierung.jsf";
	private static final String PAGE_RESERVATION_JSF = "reservation.jsf";
	private static final String PAGE_MYEVENT_DETAIL_JSF = "myEventDetail.jsf";
	private static final String PAGE_EDITEVENT_JSF = "editEvent.jsf";
	private static final String PAGE_NEWEVENT_JSF = "newEvent.jsf";
	
	
	public static String getManagerReservation(){
		return PAGE_MANAGER_RESERVATION_JSF;
	}
	
	public static String getMyEvents() {
		return PAGE_MYEVENTS_JSF;
	}
	
	public static String getHome() {
		return PAGE_HOME_JSF;
	}
	
	public static String getLogin() {
		return PAGE_LOGIN_JSF;
	}
	
	public static String getRegister() {
		return PAGE_REGISTER_JSF;
	}
	
	public static String getMyEventDetail() {
		return PAGE_MYEVENT_DETAIL_JSF;
		
	}

	public static String getReservation() {
		return PAGE_RESERVATION_JSF;
	
	}

	public static String getEditEvent() {
		return PAGE_EDITEVENT_JSF;
	}
	
	public static String getNewEvent() {
		return PAGE_NEWEVENT_JSF;
	}
	
}
