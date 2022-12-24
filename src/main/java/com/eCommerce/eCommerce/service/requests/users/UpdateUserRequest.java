package com.eCommerce.eCommerce.service.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	private String firstName;
	private String lastName;
	private String postalCode;
}
