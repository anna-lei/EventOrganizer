package de.auc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Implementierung der Entität Event
 *
 */
@Entity
@Table(name="event")
public class Event {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer eventid;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String location;
	@Column
	private Date date;
	@Column
	private Integer numberOfTickets;
	@Column
	private Double price;
	@Column
	private boolean publicly;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	@OneToMany(mappedBy="event")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public Event() {

	}

	public Event( String name, String description, String location, Date date, Integer numberOfTickets, 
			Double price, boolean publicly, User user) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
		this.price = price;
		this.publicly = publicly;
		this.user = user;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public boolean isPublicly() {
		return publicly;
	}

	public void setPublicly(boolean publicly) {
		this.publicly = publicly;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}