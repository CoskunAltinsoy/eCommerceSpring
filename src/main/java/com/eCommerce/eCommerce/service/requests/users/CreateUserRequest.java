package com.eCommerce.eCommerce.service.requests.users;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String email;
	private String firstName;
	private String lastName;
	private String postalCode;
}
