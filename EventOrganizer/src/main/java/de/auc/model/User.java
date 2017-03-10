package de.auc.model;

public class User {
	
	private String name;
	private String prename;
	private String date;
	private String mail;
	private String password;
	
	
	
	public User(String name, String prename, String date, String mail, String password) {
		super();
		this.name = name;
		this.prename = prename;
		this.date = date;
		this.mail = mail;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}



	public void setUser(String name) {
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


}
