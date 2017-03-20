package de.auc.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.auc.services.UserService;

@ManagedBean
public class UserConverter implements Converter{
	
	@ManagedProperty("#{userService}")
	private UserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(userService.getUserByName(value)==null) {
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	

}
