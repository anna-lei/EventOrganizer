package de.auc.services.interfaces;

import java.util.Date;

import de.auc.model.User;

public interface ILoginService {

	
	boolean login(User userToLogin, String password);
	
	void logout();

	void register(String name, String prename, Date date, String mail, String password1, String password2,
			boolean managerflag);

	User getActiveUser();

	void setActiveUser(User activeUser);

}