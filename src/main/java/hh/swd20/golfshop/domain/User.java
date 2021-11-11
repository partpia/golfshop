package hh.swd20.golfshop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", nullable = false, updatable = false)
	private Long userId;
	
	@JsonIgnore
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	@JsonIgnore
	private String firstName;
	
	@JsonIgnore
	private String lastName;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String phoneNumber;
	
	@JsonIgnore
	@Column(name = "role", nullable = false)
	private String role;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
	private List<Product> sellings;
	
	public User() {
		super();
		this.username = null;
		this.passwordHash = null;
		this.firstName = null;
		this.lastName = null;
		this.email = null;
		this.phoneNumber = null;
		this.role = null;
	}
	
	public User(String username, String passwordHash, String firstName, String lastName, String email,
			String phoneNumber, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
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
	
	public void setSellings(List<Product> sellings) {
		this.sellings = sellings;
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
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
		
	public List<Product> getSellings() {
		return sellings;
	}

	@Override
	public String toString() {
		return "User: username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber;
	}
}
