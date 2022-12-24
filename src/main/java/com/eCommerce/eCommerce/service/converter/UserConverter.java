package com.eCommerce.eCommerce.service.converter;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.responses.users.GetAllUsersResponse;

@Component
public class UserConverter {

	public static GetAllUsersResponse convert(User from) {
		return new GetAllUsersResponse(from.getId(),from.getEmail(), from.getFirstName(),
				                       from.getLastName(), from.getPostalCode());
	}
}
