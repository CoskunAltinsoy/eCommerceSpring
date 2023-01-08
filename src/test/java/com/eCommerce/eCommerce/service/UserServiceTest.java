package com.eCommerce.eCommerce.service;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.exception.UserNotActiveException;
import com.eCommerce.eCommerce.exception.UserNotFoundException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;
import com.eCommerce.eCommerce.service.requests.users.CreateUserRequest;
import com.eCommerce.eCommerce.service.requests.users.UpdateUserRequest;

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
	
	@Test
	void testGetUserById_whenUserIdExist_itShouldReturnUserDto() {
		Long id = 1L;
		User user = generateUser(id);
		UserDto userDto = generateUserDto(id);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
		Mockito.when(userConverter.convert(user)).thenReturn(userDto);
		
		UserDto result = userService.getUserById(id);
		
		assertEquals(result, userDto);
		
		Mockito.verify(userRepository).findById(id);
		Mockito.verify(userConverter).convert(user);
	}
	
	@Test
	void testGetUserById_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
		Long id = 1L;
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> userService.getUserById(id));
		
		Mockito.verify(userRepository).findById(id);
	    Mockito.verifyNoInteractions(userConverter);
	}
	
	@Test
	public void testCreateUser_itShouldReturnCreatedUserDto() {
		String email = "firstname@gmail.com";
		CreateUserRequest createUserRequest = 
				new CreateUserRequest(email,"FirstName", "Lastname");
		User user = new User(email, "FirstName", "Lastname", true);
		User savedUser = new User(email, "FirstName", "Lastname", true);
		UserDto userDto = new UserDto(1L, email, "FirstName", "Lastname", true);
		
		Mockito.when(userRepository.save(user)).thenReturn(savedUser);
		Mockito.when(userConverter.convert(savedUser)).thenReturn(userDto);
		
		UserDto result = userService.createUser(createUserRequest);
		
		assertEquals(result, userDto);
		
		Mockito.verify(userRepository).save(user);
		Mockito.verify(userConverter).convert(savedUser);
	}
	
	@Test
	public void testUpdateUser_whenUserIdExistAndUserIsAcive_itShouldReturnUpdatedUserDto() {
		Long id = 1L;
		UpdateUserRequest updateUserRequest = 
				new UpdateUserRequest(1L,"firstname@gmail.com","FirstName2", "Lastname2");
		User user = new User(1L, "firstname@gmail.com", "FirstName", "Lastname", true);
		User updateUser = new User(1L, "firstname@gmail.com", "FirstName2", "Lastname2", true);
		User savedUser = new User(1L, "firstname@gmail.com", "FirstName2", "Lastname2", true);
		UserDto userDto = new UserDto(1L, "firstname@gmail.com", "FirstName2", "Lastname2", true);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
		Mockito.when(userRepository.save(updateUser)).thenReturn(savedUser);
		Mockito.when(userConverter.convert(savedUser)).thenReturn(userDto);
		
		UserDto result = userService.updateUser(updateUserRequest);
		
		assertEquals(result, userDto);
		
	//  Mockito.verify(userRepository).findById(id);
		Mockito.verify(userRepository).save(updateUser);
		Mockito.verify(userConverter).convert(savedUser);
	}
	
	@Test
	public void testUpdateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
		Long id = 1L;
		UpdateUserRequest updateUserRequest = 
				new UpdateUserRequest(1L,"firstname@gmail.com","FirstName2", "Lastname2");
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
	
		assertThrows(UserNotFoundException.class, () -> userService.updateUser(updateUserRequest)); 
		
        Mockito.verify(userRepository).findById(id);
		Mockito.verifyNoMoreInteractions(userRepository);
		Mockito.verifyNoMoreInteractions(userConverter);
	}
	
	@Test
	public void testUpdateUser_whenUserIdExistButUserIsIdNotActive_itShouldThrowUserNotActiveException() {
		Long id = 1L;
		UpdateUserRequest updateUserRequest = 
				new UpdateUserRequest(id,"firstname@gmail.com","FirstName2", "Lastname2");
		User user = new User(id,"firstname@gmail.com","FirstName", "Lastname",false);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
	
		assertThrows(UserNotActiveException.class, () -> userService.updateUser(updateUserRequest)); 
		
        Mockito.verify(userRepository).findById(id);
		Mockito.verifyNoMoreInteractions(userRepository);
		Mockito.verifyNoMoreInteractions(userConverter);
	}
	
	@Test
	public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActivateFalse() {
		Long id = 1L;

		User user = new User(id,"firstname@gmail.com","FirstName", "Lastname",true);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
	
		userService.deactiveUser(id);

        Mockito.verify(userRepository).findById(id);
	}
	
	@Test
	public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
		Long id = 1L;
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
	
		assertThrows(UserNotFoundException.class, () -> userService.deactiveUser(id));

        Mockito.verify(userRepository).findById(id);
        Mockito.verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActivateTrue() {
		Long id = 1L;

		User user = new User(id,"firstname@gmail.com","FirstName", "Lastname", false);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
	
		userService.activeUser(id);

        Mockito.verify(userRepository).findById(id);
	}
	
	@Test
	public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
		Long id = 1L;
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
	
		assertThrows(UserNotFoundException.class, () -> userService.activeUser(id));

        Mockito.verify(userRepository).findById(id);
        Mockito.verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void testDeleteUser_whenUserIdExist_itShouldDeleteUser() {
		Long id = 1L;

		User user = new User(id,"firstname@gmail.com","FirstName", "Lastname", false);
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
	
		userService.deleteUser(id);

        Mockito.verify(userRepository).findById(id);
        Mockito.verify(userRepository).deleteById(id);
	}
	
	@Test
	public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {
		Long id = 1L;
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
	
		assertThrows(UserNotFoundException.class, () -> userService.deleteUser(id));

        Mockito.verify(userRepository).findById(id);
        Mockito.verifyNoMoreInteractions(userRepository);
	}

}
