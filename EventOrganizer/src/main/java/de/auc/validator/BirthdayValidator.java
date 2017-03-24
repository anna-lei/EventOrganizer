package de.auc.validator;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Implementierung der Validierung bezogen auf das Geburtsdatum des zu registrierenden Users.
 * @author anwender
 *
 */
@ManagedBean
public class BirthdayValidator {
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null) {
        	FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Bitte geben Sie ein Geburtsdatum ein.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
        } else {
        	Date birthday = (Date)value;
            Calendar today = Calendar.getInstance();
            today.set(Calendar.YEAR, today.get(Calendar.YEAR)-16);
            // Für die Registrierung ist ein Mindestalter von 16 Jahren vorgesehen.
    		if(birthday.after(today.getTime())) {
    			FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
    					"Zur Registrierung müssen Sie mindestens 16 Jahre alt sein.", "");
    			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);

    		}
		
        }

	}
}
