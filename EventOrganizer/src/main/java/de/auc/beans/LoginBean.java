package de.auc.beans;

import javax.enterprise.context.RequestScoped;
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
	
	@Inject
	private PageRenderingService pageRenderingService;
	
	
	public String startLogin() {
		return pageRenderingService.getLogin();
	}
	
	
	public String logout(){
		loginService.logout();
		return null;
	}
	
	public String login(){
		if(loginService.login(user, password)){
			if(false){
				return pageRenderingService.getEventDetail();
			}else {
				return pageRenderingService.getHome();
			}
		}
		return pageRenderingService.getLogin();
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
