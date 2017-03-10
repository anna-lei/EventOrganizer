package de.auc.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.auc.services.LoginService;

@ManagedBean
@RequestScoped
public class LoginBean {
	private String username;
	private String password;
	
	@ManagedProperty("#{loginService}")
	private LoginService loginService;
	
	public String login(){
		if(loginService.login(username, password)){
			return "anwenderHome.jsf";
		}
		return "index.html";
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}
	
	public LoginService getLoginService(){
		return loginService;
	}
	
	public void setLoginService(LoginService loginService){
		this.loginService = loginService;
	}
}
