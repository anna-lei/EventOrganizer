package de.auc.services;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;

@Named(value="loginService")
@SessionScoped
public class LoginService implements Serializable{
	private static final long serialVersionUID = 9199053082352550746L;
	
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
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public void register(String name, String prename, Date date, String mail, String password1, String password2,
			boolean managerflag) {
		User user = new User(name, prename, date, mail, password1, managerflag);
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