package de.auc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue
	@OneToMany(mappedBy="user_id")
	private Integer id;
	private String code;
	private Integer numberOfTickets;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@JoinColumn(name="eventid")
	private Event event;
	
	public Reservation(Integer id, String code, Integer numberOfTickets, User user, Event event) {
		super();
		this.id = id;
		this.code = code;
		this.numberOfTickets = numberOfTickets;
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

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
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
