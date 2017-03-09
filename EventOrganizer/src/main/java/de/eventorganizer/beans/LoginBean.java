package de.eventorganizer.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginBean {
	private String name = null;
	private String password = null;
	private boolean failLogin = false;

	public String login() {
		failLogin = true;
		return "login.jsf";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFailLogin() {
		return failLogin;
	}

	public void setFailLogin(boolean failLogin) {
		this.failLogin = failLogin;
	}
	
}
