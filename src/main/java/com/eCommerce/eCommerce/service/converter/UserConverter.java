package com.eCommerce.eCommerce.service.converter;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.model.User;

@Component
public class UserConverter {

	public static UserDto convert(User from) {
		return new UserDto(from.getId(),from.getEmail(), from.getFirstName(),
				                       from.getLastName(), from.getPostalCode());
	}
	
}
