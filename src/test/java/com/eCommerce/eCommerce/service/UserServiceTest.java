package com.eCommerce.eCommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;

class UserServiceTest extends TestSupport{

	private UserConverter userConverter;
	private UserRepository userRepository;
	
	private UserService userService;
	
	@BeforeEach
	void setUp() throws Exception {
		userConverter = Mockito.mock(UserConverter.class);
		userRepository = Mockito.mock(UserRepository.class);
		
		userService = new UserService(userRepository, userConverter);
	}

	@Test
	void testGetAllUsers_itShouldReturnUserDtoList() {
		List<User> userList = generateUsers();
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		Mockito.when(userConverter.convert(userList)).thenReturn(generateUserDtoList(userList));
	
	}

}
