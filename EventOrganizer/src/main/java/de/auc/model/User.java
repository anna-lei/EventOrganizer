package de.auc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue
	private Integer userid;
	private String name;
	private String prename;
	@Temporal(TemporalType.TIMESTAMP)
	private String date;
	private String mail;
	private String password;
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	public User(String name, String prename, String date, String mail, String password) {
		this.name = name;
		this.prename = prename;
		this.date = date;
		this.mail = mail;
		this.password = password;

	}

	public Integer getId() {
		return userid;
	}

	public void setId(Integer id) {
		this.userid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}	
	
	



}
