package de.auc.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.EventService;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@Named(value = "eventDetailBean")
@RequestScoped
public class EventDetailBean implements Serializable {

	
	@Inject
	private LoginService loginService;
	
	@Inject
	private EventService eventService;

	public String eventDetails(int eventid) {
		if (loginService.getActiveUser() != null) {
			if (loginService.getActiveUser().isManagerflag()) {
				if(eventService.getEventById(eventid).getUser()==loginService.getActiveUser()){
					return PageRenderingService.getMyEventDetail();
				}else{
					return PageRenderingService.getReservation();
				}
			} else {
				return PageRenderingService.getReservation();
			}
		} else {
			return PageRenderingService.getReservation();
		}
	}
	
	

}
