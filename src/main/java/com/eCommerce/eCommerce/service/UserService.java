package com.eCommerce.eCommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.exception.UserNotActiveException;
import com.eCommerce.eCommerce.exception.UserNotFoundException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;
import com.eCommerce.eCommerce.service.requests.users.CreateUserRequest;
import com.eCommerce.eCommerce.service.requests.users.UpdateUserRequest;

import ch.qos.logback.classic.Logger;

@Service
public class UserService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserService.class);
	
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
		User user = findUserById(id);
		return this.userConverter.convert(userRepository.save(user));
	}
	
	public UserDto createUser(CreateUserRequest createUserRequest) {
		User user = new User(createUserRequest.getEmail(),createUserRequest.getFirstName(),
				             createUserRequest.getLastName(), createUserRequest.getPostalCode(),
				             true);
		return this.userConverter.convert(userRepository.save(user));
	}
	
	public UserDto updateUser(UpdateUserRequest updateUserRequest) {
		checkUserActivation(updateUserRequest.getId());
		User user = findUserById(updateUserRequest.getId());
		user.setEmail(updateUserRequest.getEmail());
		user.setFirstName(updateUserRequest.getFirstName());
		user.setLastName(updateUserRequest.getLastName());
		user.setPostalCode(updateUserRequest.getPostalCode());
		return this.userConverter.convert(userRepository.save(user));
	}
	
	public void deactiveUser(Long id) {
		User user = findUserById(id);
		user.setIsActive(false);
	}
	
	public void activeUser(Long id) {
		User user = findUserById(id);
		user.setIsActive(true);
	}
	
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}
	
	private User findUserById(Long id) {
		return this.userRepository.findById(id)
		           .orElseThrow(() -> new UserNotFoundException("User couldn't be found "+id));
	}
	
	private void checkUserActivation(Long id) {
		User user = findUserById(id);
		if(!user.getIsActive()) {
			logger.warn(String.format("The user wanted update is not active %s", user.getEmail()));
			throw new UserNotActiveException("User Is Not Active");
		}
	}
}
