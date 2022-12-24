package com.eCommerce.eCommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.responses.users.GetAllUsersResponse;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<GetAllUsersResponse> getAllUser() {
		
	}
}
