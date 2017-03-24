package de.auc.beans;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

/**
 * Bean, die dem Header zugeh�rig ist und die Weiterleitung auf andere JSF-Seiten implementiert.
 *
 */
@Named(value="headerBean")
@ApplicationScoped
public class HeaderBean implements Serializable{

	private static final long serialVersionUID = -1648932165104363219L;

	@Inject
	private LoginService loginService;
	
	
	public String login() {
		return PageRenderingService.getLogin();
	}
	
	public String logout() {
		loginService.logout();
		return PageRenderingService.getLogin();
	}
	
	public String home() {
		return PageRenderingService.getHome();
	}
	
	public boolean addManagerMenu() {
		if(loginService.getActiveUser()==null) {
			return false;
		} else {
			return loginService.getActiveUser().isManagerflag();
		}
		
	}
	
	public String managerEvents() {
		return PageRenderingService.getMyEvents();
	}
	
	public String managerReservations() {
		return PageRenderingService.getManagerReservation();
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
	
}
