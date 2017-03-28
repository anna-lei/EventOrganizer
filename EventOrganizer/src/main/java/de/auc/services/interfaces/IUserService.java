package de.auc.services.interfaces;

import javax.transaction.Transactional;

import de.auc.model.User;

public interface IUserService {

	
	void addUser(User user);

	
	User getUserByName(String mail);

}