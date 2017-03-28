package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import de.auc.services.interfaces.IUserService;

/**
 * �ber die E-Mail wird der User eindeutig identifiziert.
 * Es gilt also zu �berpr�fen, ob die Mail bereits verwendet wurde.
 *
 */
@ManagedBean
public class UserExistMailValidator {
	@ManagedProperty("#{userService}")
	private IUserService userService;
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		//Existiert bereits ein User mit dieser Mail
		if(userService.getUserByName(value.toString())!=null) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der User mit dieser E-Mail-Adresse existiert bereits.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
		if(value.toString().length()>50) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Die E-Mail-Adresse ist zu lang.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);

		} 
		//Regex zur Pr�fung des Mailformats
		if(!value.toString().matches("^[a-zA-Z�������0-9_.+-]+@[a-zA-Z�������0-9_.+-]+\\.[a-zA-Z]+$")) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Es handelt sich um keine valide E-Mail-Adresse.", "");
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
