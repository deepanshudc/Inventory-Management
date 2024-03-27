package com.project.Inventory.Management.App.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserModel {
	
	public UserModel() {
		super();
	}

		@NotBlank(message="is required")
		@Pattern(regexp="^[a-zA-Z]*$" , message="should be alphabetical only")
		private String userName;
		
		@NotBlank(message="is required")
		@Pattern(regexp="^[a-zA-Z0-9]*$",message="should be alphanumeric only")
		private String password;
		
		@NotBlank(message="is required")
		@Pattern(regexp="^[A-Z]*$" , message="should be capital letter only")
		private String authority;

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

		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}
		
		
		

}
