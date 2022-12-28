package com.eCommerce.eCommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.eCommerce.eCommerce.dto.UserDto;
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
		List<UserDto> userDtoList = generateUserDtoList(userList);
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		Mockito.when(userConverter.convert(userList)).thenReturn(generateUserDtoList(userList));
		
		List<UserDto> result = userService.getAllUsers();
		
		assertEquals(result, userDtoList);
		
		Mockito.verify(userRepository).findAll();
		Mockito.verify(userConverter).convert(userList);
	}
	
	@Test
	void testGetUserByEmail_whenUserIdExist_itShouldReturnUserDto() {
		List<User> userList = generateUsers();
		List<UserDto> userDtoList = generateUserDtoList(userList);
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		Mockito.when(userConverter.convert(userList)).thenReturn(generateUserDtoList(userList));
		
		List<UserDto> result = userService.getAllUsers();
		
		assertEquals(result, userDtoList);
		
		Mockito.verify(userRepository).findAll();
		Mockito.verify(userConverter).convert(userList);
	
	}

}
