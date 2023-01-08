package com.eCommerce.eCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.eCommerce.dto.userDetailsDtos.CreateUserDetailsRequest;
import com.eCommerce.eCommerce.dto.userDetailsDtos.UpdateUserDetailsRequest;
import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;
import com.eCommerce.eCommerce.service.UserDetailsService;

@RestController
@RequestMapping("/api/userdetails")
public class UserDetailsController {

	private final UserDetailsService userDetailsService;
	
	@Autowired
	public UserDetailsController(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest createUserDetailsRequest){
		return ResponseEntity.ok(this.userDetailsService.createUserDetails(createUserDetailsRequest));
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDetailsDto> updateUserDetails(@RequestBody UpdateUserDetailsRequest updateUserDetailsRequest){
		return ResponseEntity.ok(this.userDetailsService.updateUserDetails(updateUserDetailsRequest));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserDetails(@PathVariable("id") Long id) {
		this.userDetailsService.deleteUserDetails(id);
		return (ResponseEntity<Void>) ResponseEntity.ok();
	}
}
