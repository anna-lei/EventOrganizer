package de.auc.services;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import de.auc.model.User;
import de.auc.services.interfaces.IUserService;

/**
 * Implementierung der Services bezogen auf einen User.
 */
@Named(value="userService")
@ApplicationScoped
public class UserService implements Serializable, IUserService {

	private static final long serialVersionUID = 126171421969002787L;

	@Inject
	private EntityManager entityManager;

	public UserService() {
		
	}
	
	/**
	 * Fügt einen neuen User hinzu.
	 * @param user
	 */
	@Override
	public void addUser(User user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt den User zu der mitgegebenen Mail zurück.
	 * @param mail
	 * @return
	 */
	@Override
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