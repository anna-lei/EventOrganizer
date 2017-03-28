package de.auc.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.auc.model.Event;
import de.auc.services.interfaces.IEventService;

/**
 * Dieser Converter sucht zu einer ID das Event und gibt das Objekt zurück.
 *
 */
@ManagedBean
public class EventConverter implements Converter{

	
	@ManagedProperty("#{eventService}")
	private IEventService eventService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) {
			return value;
		}
		//Dieser Fall wird für ein neues Event genutzt. Das leere Event wird dann durch die entsprechende JSF-Seite gefüllt.
		if(value.equals("new")){
			return new Event();
			
		} else {
			return eventService.getEventById(Integer.parseInt(value));
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return null;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}


	

}
