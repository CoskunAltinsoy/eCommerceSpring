package com.eCommerce.eCommerce.service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.model.User;

@Component
public class UserConverter {

	public UserDto convert(User from) {
		return new UserDto(from.getId(),from.getEmail(), from.getFirstName(),
				                       from.getLastName(), from.getPostalCode(),from.getIsActive());
	}

	public List<UserDto> convert(List<User> fromList) {
		return fromList.stream().map(from -> new UserDto(from.getId(),from.getEmail(), from.getFirstName(),
				                from.getLastName(), from.getPostalCode(), from.getIsActive()))
				                .collect(Collectors.toList());
	}
}
