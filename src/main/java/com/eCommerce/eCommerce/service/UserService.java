package com.eCommerce.eCommerce.service;

import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
