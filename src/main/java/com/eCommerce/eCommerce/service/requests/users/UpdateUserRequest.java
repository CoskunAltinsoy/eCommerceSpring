package com.eCommerce.eCommerce.service.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
}
