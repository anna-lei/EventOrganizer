package de.auc.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@Named(value="headerBean")
@ApplicationScoped
public class HeaderBean {
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
		}
		return loginService.getActiveUser().isManagerflag();
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
