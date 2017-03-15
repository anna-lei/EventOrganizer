package de.auc.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	private static final String PAGE_RESERVATION_JSF = "reservation.jsf";
	

	public static String getManagerDetail(){
		return PAGE_MANAGER_DETAIL_JSF;
	}
	
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
	
	public static String getEventDetail() {
		return PAGE_EVENT_DETAIL_JSF;
		
	}

	public static String getReservation(int eventid) {
		System.out.println(PAGE_RESERVATION_JSF + "?eventid=" + eventid);
		return PAGE_RESERVATION_JSF + "?eventid=" + eventid;
	
	}
	
}
