package de.auc.services;


import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.services.interfaces.ILoginService;
import de.auc.services.interfaces.IReservationEventService;

/**
 * Dieser Service implementiert das Hinzufügen einer neuen Reservierung, die einem User und einem Event zugeordnet ist.
 *
 */
@Named(value="reservationEventService")
@RequestScoped
public class ReservationEventService implements Serializable, IReservationEventService{
	
	private static final long serialVersionUID = -663880328193053308L;

	@Inject
	private EntityManager entityManager;
		
	@Inject
	private ILoginService loginService;
	
	
	@Override
	public Reservation reserve(Event event, Integer selectedTickets) {
		try {
			entityManager.getTransaction().begin();
			event.setNumberOfTickets(event.getNumberOfTickets()-selectedTickets);
			entityManager.merge(event);
			Reservation reservation = new Reservation(generateCode(), selectedTickets, loginService.getActiveUser(), event);
			entityManager.persist(reservation);
			entityManager.getTransaction().commit();
			return reservation;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public String generateCode() {
		return UUID.randomUUID().toString();
	}

}
