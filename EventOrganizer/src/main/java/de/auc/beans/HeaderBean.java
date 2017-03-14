package de.auc.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.LoginService;

@Named(value="headerBean")
@RequestScoped
public class HeaderBean {
	@Inject
	private LoginService loginService;
	
	public String logout(){
		loginService.logout();
		return null;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	

}
