package de.auc.beans;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.services.LoginService;
import de.auc.services.PageRenderingService;


@Named(value="registerBean")
@RequestScoped
public class RegisterBean {
	private String name;
	private String prename;
	private String date;
	private String mail;
	private String password1;
	private String password2;
	private boolean managerflag;
	
	@Inject
	private LoginService loginService;
	
	@Inject
	private PageRenderingService pageRenderingService;
	
	public String startRegister() {
		return pageRenderingService.getRegister();
	}
	
	public String register() {
		if(loginService.register(name, prename, date, mail, password1, password2, managerflag)){
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


	public boolean isManagerflag() {
		return managerflag;
	}

	public void setManagerflag(boolean managerflag) {
		this.managerflag = managerflag;
	}
	
	
	
}
