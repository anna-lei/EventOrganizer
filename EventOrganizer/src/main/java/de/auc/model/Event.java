package de.auc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EVENT")
public class Event {
	@Id
	@GeneratedValue
	private Integer eventid;
	private String name;
	private String description;
	private String location;
	@Temporal(TemporalType.TIMESTAMP)
	private String date;
	private Integer numberOfTickets;
	private Double price;
	private boolean publicly;
	@OneToMany(mappedBy="event")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	@OneToOne
	private User user;
	
	
	
	public Event() {

	}

	

	public Event(Integer eventid, String name, String description, String location, String date,
			Integer numberOfTickets, Double price, boolean publicly, List<Reservation> reservations, User user) {
		super();
		this.eventid = eventid;
		this.name = name;
		this.description = description;
		this.location = location;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
		this.price = price;
		this.publicly = publicly;
		this.reservations = reservations;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
