package com.example.ertriage3;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 5139838860163849957L;
	
	private String userName;
	private String password;
	private String permissions;
	
	public User(String userName, String password, String permissions) {
		this.userName = userName;
		this.password = password;
		this.permissions = permissions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
		
		
}
