package com.eCommerce.eCommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.exception.UserNotFoundException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserConverter userConverter;
	
	@Autowired
	public UserService(UserRepository userRepository, 
			           UserConverter userConverter) {
		this.userRepository = userRepository;
		this.userConverter = userConverter;
	}
	
	public List<UserDto> getAllUser() {
		return this.userRepository.findAll().stream().map(u -> userConverter.convert(u))
				                                           .collect(Collectors.toList());
	}
	
	public UserDto getUserById(Long id) {
		User user = this.userRepository.findById(id)
				           .orElseThrow(() -> new UserNotFoundException("User couldn't be found "+id));
		return this.userConverter.convert(user);
	}
}
