package hh.swd20.golfshop.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignUpForm {
	
	@NotEmpty(message = "Please choose a username")
	@Size(min=5, max=30, message = "Size must be between 5 and 30")
	private String username = "";
	
	@NotEmpty(message = "Please fill in password")
	@Size(min=8, max=30, message = "Size must be between 8 and 30")
	private String password = "";
	
	@NotEmpty(message = "Please fill in password")
	@Size(min=8, max=30, message = "Size must be between 8 and 30")
	private String passwordCheck = "";
	
	@NotEmpty(message = "Please fill in your first name")
	private String firstName;
	
	@NotEmpty(message = "Please fill in your last name")
	private String lastName;
	
	@NotEmpty(message = "Please fill in your first email")
	private String email;
	
	@NotEmpty(message = "Please fill in your first phonenumber")
	private String phoneNumber;
	
	@NotEmpty
	private String role = "USER";

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getRole() {
		return role;
	}
	
}
