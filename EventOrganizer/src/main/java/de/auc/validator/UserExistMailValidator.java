package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import de.auc.services.UserService;

@ManagedBean
public class UserExistMailValidator {
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(userService.getUserByName(value.toString())!=null) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der User mit dieser E-Mail-Adresse existiert bereits.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
		
		if(!value.toString().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9_.+-]+\\.[a-zA-Z]+$")) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Es handelt sich um keine valide E-Mail-Adresse.", "");
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
