package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import de.auc.model.User;

@Named(value="userService")
@ApplicationScoped
public class UserService implements Serializable {

	private static final long serialVersionUID = 126171421969002787L;

	private List<User> users = new ArrayList<User>();	
	
	@Inject
	private EntityManager entityManager;

	public UserService() {
		
		
		User user2 = new User("b", "b",new Date(System.currentTimeMillis()), "b", "b", false);
		
		users.add(user2);
//		entityManager.getTransaction().begin();
//		entityManager.persist(user1);
//		entityManager.getTransaction().commit();
//		
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
