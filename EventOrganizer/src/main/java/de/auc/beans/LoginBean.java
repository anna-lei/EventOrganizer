package de.auc.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;
import de.auc.services.PageRenderingService;
import de.auc.services.interfaces.ILoginService;

/**
 * Implementierung der Login-Seite zur Weiterleitung des Users und Passworts an den LoginService
 *
 */
@Named(value="loginBean")
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 8805804134820177836L;
	private Integer password;
	private User user;
	
	@Inject
	private ILoginService loginService;

	
	/**
	 * Ist der User oder das Passwort falsch, sorgt diese Methode dafür, dass auf der Seite verweilt wird.
	 * Ansonsten wird auf die Startseite verwiesen. 
	 */
	public String login(){
		if(loginService.login(user, password)){
			return PageRenderingService.getHome();
		}

		if(FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die E-Mail-Adresse oder das Passwort ist nicht korrekt.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
		}
		return null;
	}
	
	public void setPassword(Integer password) {
		this.password = password;
	}
	
	public Integer getPassword(){
		return password;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}