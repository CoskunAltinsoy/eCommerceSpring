package com.eCommerce.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.eCommerce.dto.userDtos.CreateUserRequest;
import com.eCommerce.eCommerce.dto.userDtos.UpdateUserRequest;
import com.eCommerce.eCommerce.dto.userDtos.UserDto;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
		return ResponseEntity.ok(this.userService.createUser(createUserRequest));
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
		return ResponseEntity.ok(this.userService.updateUser(updateUserRequest));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> deactiveUser(@PathVariable("id") Long id) {
		this.userService.deactiveUser(id);
	    return (ResponseEntity<Void>) ResponseEntity.ok();
	}
	
//	@PatchMapping("/{id}")
//	public ResponseEntity<Void> activeUser(@PathVariable("id") Long id) {
//		this.userService.activeUser(id);
//		 return (ResponseEntity<Void>) ResponseEntity.ok();
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		this.userService.deleteUser(id);
		return (ResponseEntity<Void>) ResponseEntity.ok();
	}
}
