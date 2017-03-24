package de.auc.validator;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
/**
 * Validierung des Datums eines Events.
 * Die Validerung des Formats dd.MM.yyyy wird über die JSF-Seite gesteuert.
 */
@ManagedBean
public class DateValidator {
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value==null) {
        	FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Bitte geben Sie ein Datum.", "");
			FacesContext.getCurrentInstance().addMessage("editform:edit", editMessage);
        } else {
        	Date date = (Date)value;
     
            Calendar today = Calendar.getInstance();
            // Das Datum bezogen auf ein Event muss in der Zukunft liegen.
    		if(date.before(today.getTime())) {
    			FacesMessage editMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
    					"Das Event muss in der Zukunft liegen und im folgenden Format angegeben werden: dd.MM.yyyy", "");
    			FacesContext.getCurrentInstance().addMessage("editform:edit", editMessage);

    		}
		
        }
}}
