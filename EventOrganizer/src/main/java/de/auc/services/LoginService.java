package de.auc.services;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import de.auc.model.User;

@ManagedBean
@SessionScoped
public class LoginService {

	private User activeUser;
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	public boolean login(User userToLogin, String password) {
		if (userToLogin != null) {
			if (password.equals(userToLogin.getPassword())) {
				activeUser = userToLogin;
				return true;
			}
		}
		FacesMessage loginMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der Username oder das Passwort ist nicht korrekt.", "");
		FacesContext.getCurrentInstance().addMessage("loginform:login", loginMessage);
		return false;
	}
	
	public void logout() {
		activeUser = null;
	}
	
	public boolean register(String name, String prename, String date, String mail, String password1, String password2, boolean managerflag) {
		FacesMessage registerMessage;
		if(userService.getUserByName(mail)!=null){
			registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Der User existiert bereits.", "");
			FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
			return false;
		} else {
			if(password1.equals(password2)){
				if(password1.length() > 7){
					User user = new User(name, prename, date, mail, password1, managerflag);
					userService.addUser(user);
					activeUser = user;
					return true;
				}
				else{
					//Das Passwort ist zu kurz
					registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Das Passwort muss mindestens 8 Zeichen lang sein.", "");
					FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
					return false;
				}	
			}else {
				registerMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Die Passwörter stimmen nicht überein.", "");
				FacesContext.getCurrentInstance().addMessage("registerform:register", registerMessage);
				return false;
			}
		}
		
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}