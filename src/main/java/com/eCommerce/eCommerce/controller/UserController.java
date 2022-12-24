package com.eCommerce.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.UserService;
import com.eCommerce.eCommerce.service.requests.users.CreateUserRequest;
import com.eCommerce.eCommerce.service.requests.users.UpdateUserRequest;

@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public List<User> getAllUsers() {
		this.userService.getAllUser();
	}
	
	@GetMapping("/getById/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		this.userService.getUserById(id);
	}
	
	@PostMapping("/create")
	public void createUser(@RequestBody CreateUserRequest createUserRequest) {
		this.userService.createUser(createUserRequest);
	}
	
	@PutMapping("/{id}")
	public void updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
		this.userService.updateUser(updateUserRequest);
	}
	
	@PatchMapping("/{id}")
	public void deactiveUser(@PathVariable("id") Long id) {
		this.userService.deactiveUser(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		this.userService.deleteUser(id);
	}
}
