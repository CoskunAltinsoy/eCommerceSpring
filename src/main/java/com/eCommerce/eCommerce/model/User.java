package com.eCommerce.eCommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "is_active")
	private Boolean isActive;

	
	public User(String email, String firstName, String lastName, String postalCode, Boolean isActive) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.postalCode = postalCode;
		this.isActive = isActive;
	}
	
	
	
}
