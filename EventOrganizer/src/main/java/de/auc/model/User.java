package de.auc.model;

public class User {
	private String name;
	private String vorname;
	private String datum;
	private String mail;
	private String username;
	private String passwort;
	
	
	
	public User(String name, String vorname, String datum, String mail, String username, String passwort) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.datum = datum;
		this.mail = mail;
		this.username = username;
		this.passwort = passwort;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
}
