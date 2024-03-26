package com.project.Inventory.Management.App.model;

import jakarta.validation.constraints.NotNull;

public class UserModel {
	
	public UserModel() {
		super();
	}

		@NotNull(message="is required")
		private String userName="";
		
		@NotNull(message="is required")
		private String password="";

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
		
		
		

}
