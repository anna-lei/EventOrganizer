package de.auc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="user")
public class User {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userid;
	@Column
	private String name;
	@Column
	private String prename;
	@Column
	private Date date;
	
	@Column(unique= true)
	private String mail;
	@Column
	private String password;
	@Column
	private boolean managerflag;

	
	public User() {
		
	}

	
	public User(String name, String prename, Date date, String mail, String password,
			boolean managerflag) {
		super();
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

	public boolean isManagerflag() {
		return managerflag;
	}

	public void setManagerflag(boolean managerflag) {
		this.managerflag = managerflag;
	}	
	
}
