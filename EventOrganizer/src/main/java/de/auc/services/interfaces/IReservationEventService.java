package de.auc.services.interfaces;

import de.auc.model.Event;
import de.auc.model.Reservation;

public interface IReservationEventService {
	
	Reservation reserve(Event event, Integer selectedTickets);

	String generateCode();

}