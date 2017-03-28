package de.auc.services.interfaces;

import de.auc.model.Event;
import de.auc.model.Reservation;

public interface IReservationEventService {

	/**
	 * Ersellung einer neuen Reservierung und Ablage in der Datenbank
	 * sowie das Heruntersetzen der Anzahl an verfügbaren Tickets bezogen auf das Event.
	 * @param event
	 * @param selectedTickets
	 * @return
	 */
	Reservation reserve(Event event, Integer selectedTickets);

	String generateCode();

}