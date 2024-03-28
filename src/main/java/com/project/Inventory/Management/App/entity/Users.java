package com.project.Inventory.Management.App.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Users {
	

	@Id
	private String username;
	
	
	private String password;
	
	private boolean enabled=true;
	

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authorities authority;


	public Users(String username,String password,boolean enabled,Authorities authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority=authorities;
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
