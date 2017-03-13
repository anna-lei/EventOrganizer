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
