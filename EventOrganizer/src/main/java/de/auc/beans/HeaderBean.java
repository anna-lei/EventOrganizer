package de.auc.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import de.auc.services.LoginService;

@ManagedBean
@ViewScoped
public class HeaderBean {
	@ManagedProperty("#{loginService}")
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
