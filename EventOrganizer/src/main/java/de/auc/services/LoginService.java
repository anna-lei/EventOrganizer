package de.auc.services;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import de.auc.model.User;

@ManagedBean
@SessionScoped
public class LoginService {

	private User activeUser;
	@ManagedProperty("#{userService}")
	private UserService userService;
	
	public boolean login(String username, String password) {
		User userToLogin = userService.getUserByName(username);
		if (userToLogin != null) {
			if (password.equals(userToLogin.getPassword())) {
				activeUser = userToLogin;
				return true;
			}
		}
		return false;
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