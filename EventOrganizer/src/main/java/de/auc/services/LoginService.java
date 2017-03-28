package de.auc.services;
import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.auc.model.User;
import de.auc.services.interfaces.ILoginService;
import de.auc.services.interfaces.IUserService;

/**
 * Der Loginservice kapselt Methoden wie den Login, Logout und die Reservierung.
 * Au�erdem wird hier der aktive User gesetzt, um dessen Aktionen an diesen zu binden.
 */
@Named(value="loginService")
@SessionScoped
public class LoginService implements Serializable, ILoginService{
	private static final long serialVersionUID = 9199053082352550746L;
	
	private User activeUser;
	
	@Inject
	private IUserService userService;
	
	
	@Override
	public boolean login(User userToLogin, String password) {
		if (userToLogin != null) {
			if (password.equals(userToLogin.getPassword())) {
				activeUser = userToLogin;
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void logout() {
		activeUser = null;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	
	@Override
	public void register(String name, String prename, Date date, String mail, String password1, String password2,
			boolean managerflag) {
		User user = new User(name, prename, date, mail, password1, managerflag);
		userService.addUser(user);
		activeUser = user;
	}

	
	@Override
	public User getActiveUser() {
		return activeUser;
	}

	
	@Override
	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}