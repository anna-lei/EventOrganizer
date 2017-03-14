package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.auc.model.User;

@Named(value="userService")
@SessionScoped
public class UserService implements Serializable {
	private List<User> users = new ArrayList<User>();	

	public UserService() {
		User user = new User(1, "Claudia", "Schaefers", "18.08.1995", "cs@jee.de",  "123", true);
		User user1 = new User(2, "a", "a", "a", "a", "a", true);
		User user2 = new User(3, "b", "b", "b", "b", "b", false);
		users.add(user);
		users.add(user1);
		users.add(user2);
		
	}
	
	
	
	public String getUsername(User user) {
		return user.getMail();
	}
	
	public void addUser(User user) {
		users.add(user);
	
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
