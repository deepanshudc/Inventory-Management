package com.project.Inventory.Management.App.entity;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Authorities {
	
	@Id
	private String username;
	
		private String authority;
		
	 @OneToOne
	 @JoinColumn(name = "username")
	 private Users user;
		

	public Authorities(String username,
			 String authority,Users user) {
		super();
		this.username = username;
		this.authority = authority;
		this.user=user;
	}

	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority ="ROLE_"+authority;
	}
	
	
	
}
