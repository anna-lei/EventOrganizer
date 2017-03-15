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
	
	public String home() {
		return PageRenderingService.getHome();
	}

	public String login() {
		return PageRenderingService.getLogin();
	}
	
	public String logout() {
		loginService.logout();
		return PageRenderingService.getLogin();
	}
}
