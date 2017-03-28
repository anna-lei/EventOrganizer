package de.auc.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.auc.services.interfaces.IUserService;

/**
 * Dieser Converter sucht su einer User-ID den User und gibt diesen als Objekt zurück.
 *
 */
@ManagedBean
public class UserConverter implements Converter{
	
	@ManagedProperty("#{userService}")
	private IUserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(userService.getUserByName(value)==null) {
			//Wird kein User gefunden im Falle des Login wird eine entsprechende Facesmessage erstellt.
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die E-Mail-Adresse ist noch nicht registriert, nutzen Sie den Link zur Registrierung.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
			return null;
		}else {
			return userService.getUserByName(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return null;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	

}
