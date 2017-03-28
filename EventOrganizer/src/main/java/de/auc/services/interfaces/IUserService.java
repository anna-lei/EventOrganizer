package de.auc.services.interfaces;

import javax.transaction.Transactional;

import de.auc.model.User;

public interface IUserService {

	/**
	 * F�gt einen neuen User hinzu.
	 * @param user
	 */
	void addUser(User user);

	/**
	 * Gibt den User zu der mitgegebenen Mail zur�ck.
	 * @param mail
	 * @return
	 */
	User getUserByName(String mail);

}