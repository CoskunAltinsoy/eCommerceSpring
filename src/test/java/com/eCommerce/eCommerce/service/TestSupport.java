package com.eCommerce.eCommerce.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.eCommerce.eCommerce.dto.UserDto;
import com.eCommerce.eCommerce.model.User;

public class TestSupport {

	public static List<User> generateUsers(){
		return IntStream.range(0,5).mapToObj(i -> 
		                        new User(
				                i+"coskun.altinsoy@gmail.com",
				                i+"firstName",
				                i+"lastName",
				                i+"postalcode",
				                new Random(2).nextBoolean())).collect(Collectors.toList());
	}
	
	public static List<UserDto> generateUserDtoList(List<User> userList){
		return userList.stream().map(from -> 
		                         new UserDto(from.getId(),
		                        	from.getEmail(),
		                        	from.getFirstName(),
		                        	from.getLastName(),
		                        	from.getPostalCode())).collect(Collectors.toList());
	}
}
