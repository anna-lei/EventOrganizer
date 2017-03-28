package de.auc.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.PageRenderingService;
import de.auc.services.interfaces.IEventService;
import de.auc.services.interfaces.ILoginService;

/**
 * Diese Bean dient ausschlieﬂlich der Weiterleitung auf die verschiedenen Detailseiten zu einem Event.
 * Hier wird unterschieden zwischen Manager und Anwender.
 *
 */
@Named(value = "eventDetailBean")
@RequestScoped
public class EventDetailBean implements Serializable {


	private static final long serialVersionUID = 6046177730610293298L;

	@Inject
	private ILoginService loginService;
	
	@Inject
	private IEventService eventService;

	public String eventDetails(int eventid) {
		if (loginService.getActiveUser() != null) {
			if (loginService.getActiveUser().isManagerflag()) {
				System.out.println(eventid);
				System.out.println(eventService.getEventById(eventid).getUser().getMail());
				System.out.println(loginService.getActiveUser().getMail());
				if(eventService.getEventById(eventid).getUser()==loginService.getActiveUser()){
					return PageRenderingService.getMyEventDetail();
				}
			}
		}
		return PageRenderingService.getReservation();
		
	}
	
}
