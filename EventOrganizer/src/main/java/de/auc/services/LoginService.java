package de.auc.services;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;

@Named(value="loginService")
@SessionScoped
public class LoginService implements Serializable{

	private User activeUser;
	@Inject
	private UserService userService;
	
	public boolean login(User userToLogin, String password) {
		if (userToLogin != null) {
			if (password.equals(userToLogin.getPassword())) {
				activeUser = userToLogin;
				return true;
			}
		}
		return false;
	}
	
	public void logout() {
		activeUser = null;
	}
	
	public void register(String name, String prename, String date, String mail, String password1, String password2, boolean managerflag) {
					User user = new User(5, name, prename, date, mail, password1, managerflag);
					userService.addUser(user);
					activeUser = user;
			
		
		
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}