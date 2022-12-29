package com.eCommerce.eCommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;
import com.eCommerce.eCommerce.service.requests.users.CreateUserRequest;

class UserServiceTest extends TestSupport{

	private UserConverter userConverter;
	private UserRepository userRepository;
	
	private UserService userService;
	
	@BeforeEach
	public void setUp() throws Exception {
		userConverter = Mockito.mock(UserConverter.class);
		userRepository = Mockito.mock(UserRepository.class);
		
		userService = new UserService(userRepository, userConverter);
	}

	@Test
	public void testGetAllUsers_itShouldReturnUserDtoList() {
		List<User> userList = generateUsers();
		List<UserDto> userDtoList = generateUserDtoList(userList);
		
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		Mockito.when(userConverter.convert(userList)).thenReturn(generateUserDtoList(userList));
		
		List<UserDto> result = userService.getAllUsers();
		
		assertEquals(result, userDtoList);
		
		Mockito.verify(userRepository).findAll();
		Mockito.verify(userConverter).convert(userList);
	}
	
//	@Test
//	void testGetUserById_whenUserIdExist_itShouldReturnUserDto() {
//		Long id = 1L;
//		User user = generateUser();
//		UserDto userDto = generateUserDto(id);
//		
//		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//		Mockito.when(userConverter.convert(user)).thenReturn(userDto);
//		
//		UserDto result = userService.getUserById(id);
//		
//		assertEquals(result.getEmail(), userDto.getEmail());
//		
//		Mockito.verify(userRepository).findById(id);
//		Mockito.verify(userConverter).convert(user);
//	
//	}
	
	@Test
	public void testCreateUser_itShouldReturnCreatedUserDto() {
		String email = "coskun.altinsoy@gmail.com";
		CreateUserRequest createUserRequest = 
				new CreateUserRequest(email,"Mücahit", "Altınsoy", "06000");
		User user = new User(email, "Mücahit", "Altınsoy", "06000", true);
		User savedUser = new User(email, "Mücahit", "Altınsoy", "06000", true);
		UserDto userDto = new UserDto(1L, email, "Mücahit", "Altınsoy", "06000", true);
		
		Mockito.when(userRepository.save(user)).thenReturn(savedUser);
		Mockito.when(userConverter.convert(savedUser)).thenReturn(userDto);
		
		UserDto result = userService.createUser(createUserRequest);
		
		assertEquals(result, userDto);
		
		Mockito.verify(userRepository).save(user);
		Mockito.verify(userConverter).convert(savedUser);
	}

}
