package com.eCommerce.eCommerce.service;

import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.dto.userDetailsDtos.CreateUserDetailsRequest;
import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.model.UserDetails;
import com.eCommerce.eCommerce.repository.UserDetailsRepository;
import com.eCommerce.eCommerce.service.converter.UserDetailsConverter;

@Service
public class UserDetailsService {

	private final UserDetailsRepository userDetailsRepository;
	private final UserService userService;
	private final UserDetailsConverter userDetailsConverter;
	
	public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService,
			                  UserDetailsConverter userDetailsConverter) {
		this.userDetailsRepository = userDetailsRepository;
		this.userService = userService;
		this.userDetailsConverter = userDetailsConverter;
	}
	
	public UserDetailsDto createUserDetail(CreateUserDetailsRequest createUserDetailsRequest) {
		
		User user = userService.findUserById(createUserDetailsRequest.getUserId());
		
		UserDetails userDetails = new UserDetails(createUserDetailsRequest.getPhoneNumber(), createUserDetailsRequest.getAddress(),
				                                  createUserDetailsRequest.getCity(), createUserDetailsRequest.getCountry(),
				                                  createUserDetailsRequest.getPostCode(), user);
		
		return this.userDetailsConverter.convert(userDetailsRepository.save(userDetails));
	}
}
