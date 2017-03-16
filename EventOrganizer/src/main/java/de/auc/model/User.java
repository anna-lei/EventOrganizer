package de.auc.model;

import java.util.ArrayList;
import java.util.Date;
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
	private Date date;
	private String mail;
	private String password;
	private boolean managerflag;
	@OneToMany(mappedBy="user")
	private List<Reservation> reservations = new ArrayList<Reservation>();
	
	

	
	public User(Integer userid, String name, String prename, Date date, String mail, String password,
			boolean managerflag) {
		super();
		this.userid = userid;
		this.name = name;
		this.prename = prename;
		this.date = date;
		this.mail = mail;
		this.password = password;
		this.managerflag = managerflag;
		
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	

	public boolean isManagerflag() {
		return managerflag;
	}

	public void setManagerflag(boolean managerflag) {
		this.managerflag = managerflag;
	}	
	
	



}
