package de.auc.beans;


import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

/**
 * Diese Bean bezieht sich auf die Registrierungsseite und die Weiterleitung der Registrierung.
 *
 */
@Named(value="registerBean")
@RequestScoped
public class RegisterBean implements Serializable{
	
	private static final long serialVersionUID = 7677618880923492523L;
	private String name;
	private String prename;
	private Date date;
	private String mail;
	private String password1;
	private String password2;
	private boolean managerflag;
	
	@Inject
	private LoginService loginService;
	
	/**
	 * Die Registrierung wird durch den LoginService vorgenommen.
	 * Hier wird die Facesmessage bezogen auf die Gleichheit der Passwörter erzeugt.
	 * Die anderen werden über Validatoren erstellt.
	 * @return
	 */
	public String register() {
		//Die verschiedenen Inputs werden über Validatoren geprüft und eine entsprechende Facesmessage mitgegeben. 
		//Ist der Facescontext leer, so wird der user durch den Service registriert.
		if(FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			if(password1.equals(password2)){
				loginService.register(name, prename, date, mail, password1, password2, managerflag);
				return PageRenderingService.getHome();
				
			}else {
				FacesMessage registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die Passwörter stimmen nicht überein.", "");
				FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
				return PageRenderingService.getRegister();
			}
		} else {
			
			return PageRenderingService.getRegister();
		}	
		
	}

	public String cancel() {
		return PageRenderingService.getLogin();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	public boolean isManagerflag() {
		return managerflag;
	}

	public void setManagerflag(boolean managerflag) {
		this.managerflag = managerflag;
	}
	
}
