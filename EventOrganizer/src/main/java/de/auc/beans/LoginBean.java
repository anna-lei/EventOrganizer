package de.auc.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

/**
 * Implementierung der Login-Seite zur Weiterleitung des Users und Passworts an den LoginService
 *
 */
@Named(value="loginBean")
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 8805804134820177836L;
	private String password;
	private User user;
	
	@Inject
	private LoginService loginService;
		
	
	//TODO ich glaube die Methode wird gar nicht mehr verwendet
	public String logout(){
		loginService.logout();
		return null;
	}
	
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
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
