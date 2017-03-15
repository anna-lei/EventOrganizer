package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import de.auc.services.UserService;

@ManagedBean
public class PasswortValidator {
	@ManagedProperty("#{userService}")
	private UserService userService;

	public void validateLength(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		FacesMessage registerMessage;
		if (!value.toString().matches(".*[0-9].*")) {
			// Das Passwort enthält keine Zahl
			registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Das Passwort muss mindestens eine Zahl enthalten.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
		if (value.toString().length() < 8) {
			// Das Passwort ist zu kurz
			registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Das Passwort muss mindestens 8 Zeichen lang sein.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
		

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
