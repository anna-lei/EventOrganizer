package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class TitelValidator {
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value==null) {
			FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Bitte geben Sie einen Titel ein.", "");
			FacesContext.getCurrentInstance().addMessage("form:edit", editMessage);

		}
		else if(value.toString().length()>100) {
			FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Der Titel darf nur 100 Zeichen lang sein.", "");
			FacesContext.getCurrentInstance().addMessage("form:edit", editMessage);

		}
		
	}
}
