package de.auc.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Um eine erste Sicherheit zu implementieren in Bezug auf das Passwort wird dieses im Hash-Format abgelegt.
 */
@ManagedBean
public class PasswordConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value.hashCode();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
