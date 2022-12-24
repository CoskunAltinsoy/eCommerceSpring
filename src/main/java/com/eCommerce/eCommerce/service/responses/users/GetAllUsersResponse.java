package com.eCommerce.eCommerce.service.responses.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersResponse {
	private Long id;
	private String email;
    private String firstName;
	private String lastName;
	private String postalCode;
}
