package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import de.auc.services.interfaces.IUserService;
/**
 * Validierung des Passworts eines Users
 *
 */
@ManagedBean
public class PasswordValidator {
	
	@ManagedProperty("#{userService}")
	private IUserService userService;

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (!value.toString().matches(".*[0-9].*")) {
			// Das Passwort enthält keine Zahl
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Das Passwort muss mindestens eine Zahl enthalten.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}

		if (value.toString().length() < 8) {
			// Das Passwort ist zu kurz
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Das Passwort muss mindestens 8 Zeichen lang sein.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}