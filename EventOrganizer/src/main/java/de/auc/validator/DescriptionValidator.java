package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class DescriptionValidator {
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value==null) {
			FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Bitte geben Sie eine Beschreibung ein.", "");
			FacesContext.getCurrentInstance().addMessage("form:edit", editMessage);

		}
		else if(value.toString().length()>100) {
			FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Die Beschreibung darf nur 500 Zeichen umfassen.", "");
			FacesContext.getCurrentInstance().addMessage("form:edit", editMessage);

		}
		
	}
}
