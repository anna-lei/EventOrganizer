package de.auc.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
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
	private Event event;

	@Inject
	private ILoginService loginService;
	
	@Inject
	private IEventService eventService;

	public void eventDetails() throws IOException {
		int eventid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventid"));
		
		System.out.println("HIER::: " + eventService.getEventById(eventid).getDate() + " Event: " + eventService.getEventById(eventid).getName());
		if (loginService.getActiveUser() != null) {
			if (loginService.getActiveUser().isManagerflag()) {
				if(eventService.getEventById(eventid).getUser().getMail()==loginService.getActiveUser().getMail()){
					FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getMyEventDetail()+"?eventid="+eventid);
					
				} else {
					FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getReservation()+"?eventid="+eventid);
				}
			} else {
				FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getReservation()+"?eventid="+eventid);
			}
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getReservation()+"?eventid="+eventid);
		}
		
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
