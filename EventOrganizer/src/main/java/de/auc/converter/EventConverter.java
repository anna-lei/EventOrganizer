package de.auc.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.auc.services.EventService;


@ManagedBean
public class EventConverter implements Converter{

	@ManagedProperty("#{eventService}")
	private EventService eventService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		System.out.println("Eventconverter   " + value);
		return (value == null) ? value : eventService.getEventById(Integer.parseInt(value));
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}


	

}
