package com.eCommerce.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.dto.userDetailsDtos.CreateUserDetailsRequest;
import com.eCommerce.eCommerce.dto.userDetailsDtos.UpdateUserDetailsRequest;
import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;
import com.eCommerce.eCommerce.exception.UserDetailsNotFoundException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.model.UserDetails;
import com.eCommerce.eCommerce.repository.UserDetailsRepository;
import com.eCommerce.eCommerce.service.converter.UserDetailsConverter;

@Service
public class UserDetailsService {

	private final UserDetailsRepository userDetailsRepository;
	private final UserService userService;
	private final UserDetailsConverter userDetailsConverter;
	
	@Autowired
	public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService,
			                  UserDetailsConverter userDetailsConverter) {
		this.userDetailsRepository = userDetailsRepository;
		this.userService = userService;
		this.userDetailsConverter = userDetailsConverter;
	}
	
	public UserDetailsDto createUserDetails(CreateUserDetailsRequest createUserDetailsRequest) {
		
		User user = userService.findUserById(createUserDetailsRequest.getUserId());
		
		UserDetails userDetails = new UserDetails(createUserDetailsRequest.getPhoneNumber(), createUserDetailsRequest.getAddress(),
				                                  createUserDetailsRequest.getCity(), createUserDetailsRequest.getCountry(),
				                                  createUserDetailsRequest.getPostCode(), user);
		
		return this.userDetailsConverter.convert(userDetailsRepository.save(userDetails));
	}
	
	public UserDetailsDto updateUserDetails(UpdateUserDetailsRequest updateUserDetailsRequest) {
		
		UserDetails userDetails = findUserDetailById(updateUserDetailsRequest.getId());
		
		userDetails.setPhoneNumber(updateUserDetailsRequest.getPhoneNumber());
		userDetails.setAddress(updateUserDetailsRequest.getAddress());
		userDetails.setCity(updateUserDetailsRequest.getCity());
		userDetails.setCountry(updateUserDetailsRequest.getCountry());
		userDetails.setPostCode(updateUserDetailsRequest.getPostCode());
		
		return this.userDetailsConverter.convert(this.userDetailsRepository.save(userDetails));
	}
	
	public void deleteUserDetails(Long id) {
		findUserDetailById(id);
		userDetailsRepository.deleteById(id);
	}
	
	private UserDetails findUserDetailById(Long id) {
		return this.userDetailsRepository.findById(id)
				      .orElseThrow(() -> new UserDetailsNotFoundException("User could not found"+ id));
	}
}
