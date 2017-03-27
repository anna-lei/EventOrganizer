package de.auc.services;


import java.io.Serializable;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import de.auc.model.Event;
import de.auc.model.Reservation;

/**
 * Dieser Service implementiert das Hinzufügen einer neuen Reservierung, die einem User und einem Event zugeordnet ist.
 *
 */
@Named(value="reservationEventService")
@RequestScoped
public class ReservationEventService implements Serializable{
	
	private static final long serialVersionUID = -663880328193053308L;

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private EventService eventService;
	
	@Inject
	private LoginService loginService;
	
	/**
	 * Ersellung einer neuen Reservierung und Ablage in der Datenbank
	 * sowie das Heruntersetzen der Anzahl an verfügbaren Tickets bezogen auf das Event.
	 * @param event
	 * @param selectedTickets
	 * @return
	 */
	public Reservation reserve(Event event, Integer selectedTickets) {
		entityManager.getTransaction().begin();
		
		event.setNumberOfTickets(event.getNumberOfTickets()-selectedTickets);
		entityManager.merge(event);
		
		Reservation reservation = new Reservation(generateCode(), selectedTickets, loginService.getActiveUser(), event);
		entityManager.persist(reservation);
		//TODO Mapping zwischen Event und Reservierung
//		event.getReservations().add(reservation);
		
		entityManager.getTransaction().commit();
	
		return reservation;
	}
	
	public String generateCode() {
		return UUID.randomUUID().toString();
	}

}
