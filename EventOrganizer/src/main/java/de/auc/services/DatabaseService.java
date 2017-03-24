package de.auc.services;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Dieser Service dient der Erstellung des Entitymanagers.
 *
 */
@ApplicationScoped
public class DatabaseService implements Serializable {

	private static final long serialVersionUID = 8077242953068886253L;

	@Produces
	public EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		return emf.createEntityManager();
	}
}
