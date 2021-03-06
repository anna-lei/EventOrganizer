package de.auc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Implementierung der Entit�t Reservierung.
 *
 */

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String code;
	private Integer selectedTickets;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@ManyToOne
	@JoinColumn(name="eventid")
	private Event event;
	
	public Reservation() {
		
	}
	
	public Reservation(String code, Integer numberOfTickets, User user, Event event) {
		super();
		this.code = code;
		this.selectedTickets = numberOfTickets;
		this.user = user;
		this.event = event;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getSelectedTickets() {
		return selectedTickets;
	}

	public void setSelectedTickets(Integer selectedTickets) {
		this.selectedTickets = selectedTickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}