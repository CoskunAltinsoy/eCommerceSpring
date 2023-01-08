package com.eCommerce.eCommerce.service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;
import com.eCommerce.eCommerce.model.UserDetails;

@Component
public class UserDetailsConverter {

	public UserDetailsDto convert(UserDetails from) {
		return new UserDetailsDto(from.getPhoneNumber(), from.getAddress(), from.getCity(),
				                  from.getCountry(), from.getPostCode());
	}
	
	public List<UserDetailsDto> convert(List<UserDetails> fromList){
		return fromList.stream().map(from -> convert(from)).collect(Collectors.toList());
	}
}
