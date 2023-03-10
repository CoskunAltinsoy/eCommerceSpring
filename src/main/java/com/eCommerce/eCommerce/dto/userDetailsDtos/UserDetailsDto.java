package com.eCommerce.eCommerce.dto.userDetailsDtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto implements Serializable{

	private String phoneNumber;
	private String address;
	private String city;
	private String country;
	private String postCode;
	
}
