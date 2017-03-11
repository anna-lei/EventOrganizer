package de.auc.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;

@ManagedBean
@RequestScoped
public class LoginBean {
	private String mail;
	private String password;
	
	@ManagedProperty("#{loginService}")
	private LoginService loginService;
	
	@ManagedProperty("#{pageRenderingService}")
	private PageRenderingService pageRenderingService;
	
	public String login(){
		if(loginService.login(mail, password)){
			return pageRenderingService.getHome();
		}
		return pageRenderingService.getLogin();
	}
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
	public PageRenderingService getPageRenderingService() {
		return pageRenderingService;
	}
	public void setPageRenderingService(PageRenderingService pageRenderingService) {
		this.pageRenderingService = pageRenderingService;
	}
	
	
}
