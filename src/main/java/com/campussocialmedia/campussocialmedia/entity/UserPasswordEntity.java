package com.campussocialmedia.campussocialmedia.entity;

public class UserPasswordEntity {
	
	private String username;
	private String password;
	
	
	public UserPasswordEntity() {
		super();
	}

	public UserPasswordEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
