package com.eCommerce.eCommerce.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerce.dto.userDtos.CreateUserRequest;
import com.eCommerce.eCommerce.dto.userDtos.UpdateUserRequest;
import com.eCommerce.eCommerce.dto.userDtos.UserDto;
import com.eCommerce.eCommerce.exception.UserNotActiveException;
import com.eCommerce.eCommerce.exception.UserNotFoundException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.repository.UserRepository;
import com.eCommerce.eCommerce.service.converter.UserConverter;

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
	
	@Cacheable(value = "users")
	public List<UserDto> getAllUsers() {
//		return this.userRepository.findAll().stream().map(u -> userConverter.convert(u))
//				                                           .collect(Collectors.toList());
		return this.userConverter.convert(userRepository.findAll());
	}
	
	public UserDto getUserById(Long id) {
		User user = findUserById(id);
		return this.userConverter.convert(user);
	}
	
	@CachePut(value = "users", key ="#id")
	public UserDto createUser(CreateUserRequest createUserRequest) {
		User user = new User(createUserRequest.getEmail(),createUserRequest.getFirstName(),
				             createUserRequest.getLastName(),
				             true);
		return this.userConverter.convert(userRepository.save(user));
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public UserDto updateUser(UpdateUserRequest updateUserRequest) {
		checkUserActivation(updateUserRequest.getId());
		User user = findUserById(updateUserRequest.getId());
		user.setEmail(updateUserRequest.getEmail());
		user.setFirstName(updateUserRequest.getFirstName());
		user.setLastName(updateUserRequest.getLastName());
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
	
	@CacheEvict(value = "users", allEntries = true)
	public void deleteUser(Long id) {
		findUserById(id);
		this.userRepository.deleteById(id);
	}
	
	protected User findUserById(Long id) {
		return this.userRepository.findById(id)
		           .orElseThrow(() -> new UserNotFoundException("User couldn't be found "+id));
	}
	
//	private User findUserByEmail(String email) {
//		return this.userRepository.findUserByEmail(email)
//		           .orElseThrow(() -> new UserNotFoundException("User couldn't be found "+ email));
//	}
	
	private void checkUserActivation(Long id) {
		User user = findUserById(id);
		if(!user.getIsActive()) {
			logger.warn(String.format("The user wanted update is not active %s", user.getEmail()));
			throw new UserNotActiveException("User Is Not Active");
		}
	}
	
}
