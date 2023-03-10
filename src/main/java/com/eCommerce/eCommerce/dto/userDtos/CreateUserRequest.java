package com.eCommerce.eCommerce.dto.userDtos;

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

}
