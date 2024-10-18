package model;

import java.util.Date;

public class User {
	private String username;
	private String name;
	private String subname;
	private String password;
	private Date birthdate;
	private String email;
	private int phone;
	private int maila = 0;
	
	public User(String username, String name, String subname, String password, Date birthdate, String email, int phone, int maila) {
		this.username = username;
		this.name = name;
		this.subname = subname;
		this.password = password;
		this.birthdate = birthdate;
		this.email = email;
		this.phone = phone;
		this.maila = maila;
	}
	
	public User(String username, String name, String subname, String password, Date birthdate, String email,
			int phone) {
		this.username = username;
		this.name = name;
		this.subname = subname;
		this.password = password;
		this.birthdate = birthdate;
		this.email = email;
		this.phone = phone;
	}
	
	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public int getMaila() {
		return maila;
	}
	
	public void setMaila(int maila) {
        this.maila = maila;   
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", subname=" + subname + ", password=" + password
				+ ", birthdate=" + birthdate + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}
