package com.cashify.request;

import jakarta.validation.constraints.Pattern;

public class UserAddReq {

	@Pattern(regexp = "^[a-zA-Z0-9]{4,15}$", message = "Username must be alphanumeric and between 4 to 15 characters long.")
	private String userName;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$", message = "Password must be at least 8 to 15 characters long, contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNo;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
