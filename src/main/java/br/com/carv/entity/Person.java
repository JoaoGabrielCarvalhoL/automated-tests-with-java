package br.com.carv.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_person")
public class Person implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 0L;

	@Id 
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, length = 100)
	private String firstName; 
	
	@Column(nullable = false, length = 100)
	private String lastName; 
	
	@Column(nullable = false, length = 20)
	private String postalCode; 
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createdAt;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime updatedAt;
	
	private Boolean isActive;
	
	public Person() {
		
	}

	public Person(UUID id, String firstName, String lastName, String postalCode, 
			String email, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postalCode = postalCode;
		this.email = email;
		this.username = username;
		this.isActive = true;
	}
	
	public Person(String firstName, String lastName, String postalCode, 
			String email, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.postalCode = postalCode;
		this.email = email;
		this.username = username;
		this.isActive = true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	private void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	private void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id);
	}
	
	@PrePersist
	private void setupCreated() {
		setCreatedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	private void setupUpdated() {
		setUpdatedAt(LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postalCode=" + postalCode
				+ ", email=" + email + ", username=" + username + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", isActive=" + isActive + "]";
	}
	
	
	
	
	
	
}
