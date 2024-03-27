package com.project.Inventory.Management.App.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Users {
	

	@Id
	private String username;
	
	
	private String password;
	
	private boolean enabled=true;
	

	public Users(String username,String password,boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users() {
		super();
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
