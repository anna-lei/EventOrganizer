package de.auc.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.auc.model.User;
import de.auc.services.UserService;

@ManagedBean
@RequestScoped
public class RegisterBean {
	private String name;
	private String prename;
	private String date;
	private String mail;
	private String password1;
	private String password2;
	
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	public String register() {
		if(password1.equals(password2)){
			if(password1.length() > 4){
				User user = new User(name, prename, date, mail, password1);
				if(userService.addUser(user)) {
					System.out.println("Hier");
					return "home.jsf";
				}
				//Falls der User schon existiert
				return "register.jsf";
			}
			else{
				//Das Passwort ist zu kurz
				FacesMessage passwordToShort = new FacesMessage("Das Passwort muss mindestens 4 Zeichen lang sein.", null);
				FacesContext.getCurrentInstance().addMessage(null, passwordToShort);
				return "register.jsf";
			}	
		}else {
			//Die Passwörter sind unterschiedlich
			return "register.jsf";
		}
		
	}

	public String cancel() {
		return "login.jsf";
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
	

	public UserService getUserService(){
		return this.userService;
	}
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
}
