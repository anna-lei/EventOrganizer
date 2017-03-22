package de.auc.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.auc.model.Event;
import de.auc.services.EventService;


@ManagedBean
public class EventConverter implements Converter{

	
	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null) {
			return value;
		}
		if(value.equals("new")){
			System.out.println("hier zum zweiten Mal");
			return new Event();
			
		} else {
			System.out.println("richtig");
			return eventService.getEventById(Integer.parseInt(value));
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return null;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}


	

}
