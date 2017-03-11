package de.auc.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;


@ManagedBean
@RequestScoped
public class RegisterBean {
	private String name;
	private String prename;
	private String date;
	private String mail;
	private String password1;
	private String password2;
	
	@ManagedProperty("#{loginService}")
	private LoginService loginService;
	
	@ManagedProperty("#{pageRenderingService}")
	private PageRenderingService pageRenderingService;
	
	public String register() {
		if(loginService.register(name, prename, date, mail, password1, password2)){
			loginService.login(mail, password1);
			return pageRenderingService.getHome();
		}
		return pageRenderingService.getRegister();
		
		
	}

	public String cancel() {
		return pageRenderingService.getLogin();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	public String getPassword2() {
		return password2;
	}
	
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public PageRenderingService getPageRenderingService() {
		return pageRenderingService;
	}

	public void setPageRenderingService(PageRenderingService pageRenderingService) {
		this.pageRenderingService = pageRenderingService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
}
