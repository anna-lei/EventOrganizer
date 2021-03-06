package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * F�r den Vornamen und den Nachnamen eines Users sind nur Buchstaben vorgesehen
 *
 */
@ManagedBean
public class NameValidator {
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value.toString().length()>30) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Der Name darf nur 30 Zeichen lang sein.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}

		//Regex bezogen auf die zul�ssigen Buchstaben
		if(!value.toString().matches("[a-zA-Z�����������]+")) {
			for(FacesMessage message: FacesContext.getCurrentInstance().getMessageList()) {
				if(message.getSummary().equals("Der Name darf nur Buchstaben enthalten.")) {
					return;
				} 
			}
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Der Name darf nur Buchstaben enthalten.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
		}
	}

}