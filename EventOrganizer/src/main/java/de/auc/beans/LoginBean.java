package de.auc.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;
import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@Named(value="loginBean")
@RequestScoped
public class LoginBean {
	private String password;
	private User user;
	
	@Inject
	private LoginService loginService;
		
	
	
	
	
	public String logout(){
		loginService.logout();
		return null;
	}
	
	public String login(){
		if(loginService.login(user, password)){
			return PageRenderingService.getHome();
			
		}
		if(FacesContext.getCurrentInstance().getMessageList().isEmpty()) {
			FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die E-Mail-Adresse oder das Passwort ist nicht korrekt.", "");
			FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
		}
		return PageRenderingService.getLogin();
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
