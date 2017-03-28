package de.auc.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.Event;
import de.auc.services.PageRenderingService;
import de.auc.services.interfaces.ILoginService;
import de.auc.services.interfaces.IManagerService;

/**
 * Diese Bean nützt der gesamten Abwicklung der Managementaufgaben
 * beziehungsweise der Vermittlung zwischen den xhtml-Seiten des Managers und
 * dem Managerservice Aus diesem Grund handelt es sich um eine Sessionscoped
 * Bean.
 *
 */
@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

	private static final long serialVersionUID = 7058658488268761559L;
	private String searchText;
	private Event event;
	private String filter;

	@Inject
	private IManagerService managerService;

	@Inject
	private ILoginService loginService;

	List<Event> managerEvents = new ArrayList<Event>();

	/**
	 * In der Event-Ansicht des Managers ist es möglich nach einem Text zu
	 * suchen, jedoch auch einen Filter mitzugeben. Diese beiden Parameter aus
	 * der View werden hier an den Managerservice übergeben. Außerdem wird die
	 * jeweilige Facesmessage bezogen auf den Suchtext erstellt.
	 */
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

	/**
	 * Diese Methode ist ähnlich zur Postconstruct-Methode innerhalb der
	 * Eventbean zur Initalisierung der Trotzdem wird diese Methode auch dafür
	 * verwendet, um einen vermeintlichen Zugriff über die URL auf die Seiten
	 * des Managers abzufangen.
	 */
	@PostConstruct
	public void doManagerActions() {
		// Bei keiner Anmeldung wird auf die Startseite verwiesen
		if (loginService.getActiveUser() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getHome());
				// Löschung der Managerbean aus dem Sessionkontext
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Ist der angemeldete User ein Standardanwender wird auch dieser auf
		// die Startseite verwiesen und ausgeloggt.
		else if (!loginService.getActiveUser().isManagerflag()) {
			try {

				FacesContext.getCurrentInstance().getExternalContext().redirect(PageRenderingService.getHome());
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			managerEvents.clear();
			for (Event event : managerService.getManagerEvents()) {
				managerEvents.add(event);
			}

		}
	}

	/**
	 * Diese Methode dient ausschließlich der Anzeige, ob das Event
	 * veröffentlicht ist.
	 * 
	 * @param event
	 * @return
	 */
	public String getPublicly(Event event) {
		if (event.isPublicly()) {
			return "Ja";
		} else {
			return "Nein";
		}
	}

	/**
	 * Die Funktion Publish wird innerhalb des Managerservices verarbeitet. Hier
	 * wird ausschließlich die Facesmessage generiert.
	 */
	public void publish() {

		event.setPublicly(true);
		if (managerService.publish(event)) {
			
			// aufgrund von Sessionscoped wird hier das Event manuell gesetzt
			managerEvents = managerService.getManagerEvents();
			FacesMessage publishMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Das Event ist veröffentlicht und kann nicht mehr bearbeitet werden.", "");
			FacesContext.getCurrentInstance().addMessage("publishform:publish", publishMessage);
			
		} else {
			//Das Event muss zurückgesetzt werden, da es in der Datenbank nicht auf true gesetzt wurde
			event.setPublicly(false);
			FacesMessage publishMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Das Event konnte nicht veröffentlicht werden", "");
			FacesContext.getCurrentInstance().addMessage("publishform:publish", publishMessage);
		}

	}

	/**
	 * Die Funktion des Speicherns wird innerhalb des Managerservices verarbeitet.
	 * Hier wird ausschließlich die Weiterleitung auf die entsprechende JSF-Seite implementiert.
	 * @return
	 */
	public String save() {
		//Die verschiedenen Inputs werden über Validatoren geprüft und eine entsprechende Facesmessage mitgegeben. 
		//Ist der Facescontext leer, so wird das Event gespeichert.
		if(FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			if(managerService.saveEvent(event)){
				return PageRenderingService.getMyEventDetail();
			} else {
				//Tritt im Service ein Fehler auf, wird hier die entsprechende Message erstellt.
				FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
    					"Das Event konnte nicht gespeichert werden", "");
    			FacesContext.getCurrentInstance().addMessage("editform:edit", editMessage);
			}
		}
		return PageRenderingService.getEditEvent();
		
	}

	/**
	 * Die Funktion Hinzufügens wird innerhalb des Managerservices verarbeitet.
	 * Hier wird ausschließlich die Weiterleitung auf die entsprechende
	 * JSF-Seite implementiert.
	 * 
	 * @return
	 */
	public String add() {
		// Die verschiedenen Inputs werden über Validatoren geprüft und eine
		// entsprechende Facesmessage mitgegeben.
		// Ist der Facescontext leer, so wird das Event hinzugefügt.

		if (FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			/**
			 * zunächst hat das hinzuzufügende Event noch keine ID, denn diese
			 * wird durch die Datenbank generiert, sodass das Event der Bean
			 * hier überschrieben werden muss. Erst dann ist eine korrekte
			 * Weiterleitung über die ID in der URL möglich.
			 */
			event = managerService.addEvent(event);
			/**
			 * Die Managerevents werden hier überschrieben, da es sich um eine
			 * Sessionscoped Bean handelt. Die Postconstruct-Methode zur
			 * Befüllung der Events auf der Managerseite wird somit zu diesem
			 * Zeitpunkt nicht noch einmal aufgerufen.
			 */
			managerEvents = managerService.getManagerEvents();
			return PageRenderingService.getMyEventDetail();

		} else {

			return PageRenderingService.getNewEvent();
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
