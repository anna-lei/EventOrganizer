package de.auc.services.interfaces;

import java.util.Date;

import de.auc.model.User;

public interface ILoginService {

	/**
	 * Bei einem Login wird dieser als aktiver User gesetzt.
	 * @param userToLogin
	 * @param password
	 * @return
	 */
	boolean login(User userToLogin, String password);

	/**
	 * Bei einem Logout wird der aktive User zurückgesetzt.
	 */
	void logout();

	/**
	 * Die Registrierung wird durch den Userservice übernommen.
	 * Hier wird der zu registrierung angemeldet und als aktiver User gesetzt.
	 * @param name
	 * @param prename
	 * @param date
	 * @param mail
	 * @param password1
	 * @param password2
	 * @param managerflag
	 */
	void register(String name, String prename, Date date, String mail, String password1, String password2,
			boolean managerflag);

	User getActiveUser();

	void setActiveUser(User activeUser);

}