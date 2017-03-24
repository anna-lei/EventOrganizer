package de.auc.services;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.auc.model.User;

/**
 * Implementierung der Services bezogen auf einen User.
 */
@Named(value="userService")
@ApplicationScoped
public class UserService implements Serializable {

	private static final long serialVersionUID = 126171421969002787L;

	@Inject
	private EntityManager entityManager;

	public UserService() {
		
	}
	
	
	//TODO: Wird nicht benutzt oder?
	public String getUsername(User user) {
		return user.getMail();
	}
	
	/**
	 * Fügt einen neuen User hinzu.
	 * @param user
	 */
	@Transactional
	public void addUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}
	
	/**
	 * Gibt den User zu der mitgegebenen Mail zurück.
	 * @param mail
	 * @return
	 */
	public User getUserByName(String mail) {
		User user;
		
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u where u.mail = :mail", User.class);
		query.setParameter("mail", mail);
		try {
			user = query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
