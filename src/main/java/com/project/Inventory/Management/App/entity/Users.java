package com.project.Inventory.Management.App.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Users {
	

	@Id
	private String username;
	
	
	private String password;
	
	private boolean enabled=true;
	

	public Users(
			@NotBlank(message = "is required") @Pattern(regexp = "^[a-zA-Z]*$", message = "should be alphabetical only") String username,
			@NotBlank(message = "is required") @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "should be alphanumeric only") String password,
			boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
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
