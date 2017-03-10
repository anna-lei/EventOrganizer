package de.auc.services;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.auc.model.User;

@ManagedBean
@SessionScoped
public class UserService {
	private List<User> users = new ArrayList<User>();	

	public UserService() {
		User user = new User("Claudia", "Schaefers", "18.08.1995", "cs@jee.de",  "123");
		User user1 = new User("a", "a", "a", "a", "a");
		users.add(user);
		users.add(user1);
		
	}
	
	public String getUsername(User user) {
		return user.getMail();
	}
	
	public boolean addUser(User user) {
		if (getUserByName(user.getMail()) == null) {
			return users.add(user);
		}
		return false;
	
	}
	
	public User getUserByName(String mail) {
		for(User user: users) {
			if (user.getMail().equals(mail)) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
