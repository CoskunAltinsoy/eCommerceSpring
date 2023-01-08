package com.eCommerce.eCommerce.service.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerce.dto.userDtos.UserDto;
import com.eCommerce.eCommerce.model.User;

@Component
public class UserConverter {

	public final UserDetailsConverter userDetailsConverter;
	
	public UserConverter(UserDetailsConverter userDetailsConverter) {
		super();
		this.userDetailsConverter = userDetailsConverter;
	}

	public UserDto convert(User from) {
		return new UserDto(from.getId(),from.getEmail(), from.getFirstName(),
				                       from.getLastName(), from.getIsActive(), 
				                       userDetailsConverter.convert(from.getUserDetails()));
	}

	public List<UserDto> convert(List<User> fromList) {
		return fromList.stream().map(from -> convert(from))
				                .collect(Collectors.toList());
	}
}
