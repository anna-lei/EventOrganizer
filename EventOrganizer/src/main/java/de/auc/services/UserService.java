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
	private User aktuellUser;
	
	

	public UserService() {
		User user = new User("Claudia", "Schaefers", "18.08.1995", "cs@jee.de", "claudi", "123");
		User user1 = new User("a", "a", "a", "a", "a", "a");
		users.add(user);
		users.add(user1);
		aktuellUser = user;
		
	}
	
	public String getUsername(User user) {
		return user.getUsername();
	}
	
	public boolean addUser(String name, String vorname, String datum, String mail, String username, String passwort) {
		User user = new User(name, vorname, datum, mail, username, passwort);
		if(getUser(user.getUsername())==null) {
			users.add(user);
			return true;
		} else {
			return false;
		}
	
	}
	
	public User getUser(String username) {
		for(User user: users) {
			if (user.getName().equals(username)) {
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

	public User getAktuellUser() {
		return aktuellUser;
	}

	public void setAktuellUser(User aktuellUser) {
		this.aktuellUser = aktuellUser;
	}
}
