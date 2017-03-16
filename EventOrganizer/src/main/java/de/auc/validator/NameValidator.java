package de.auc.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class NameValidator {
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(!value.toString().matches("[a-zA-Z]*")) {
			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Der Name darf nur alphanumerische Zeichen enthalten", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);

		}
		
		

	}

}
