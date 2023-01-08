package com.eCommerce.eCommerce.dto.userDetailsDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDetailsRequest {
	
	private String phoneNumber;
	private String address;
	private String city;
	private String country;
	private String postCode;
	private Long userId;
}
