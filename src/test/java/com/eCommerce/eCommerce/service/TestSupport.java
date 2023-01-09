package com.eCommerce.eCommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;
import com.eCommerce.eCommerce.dto.userDtos.UserDto;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.model.UserDetails;

public class TestSupport {

	public static List<User> generateUsers(){
		return IntStream.range(0,5).mapToObj(i -> 
		                        new User(
				                i+"coskun.altinsoy@gmail.com",
				                i+"firstName",
				                i+"lastName",
				                new Random(2).nextBoolean())).collect(Collectors.toList());
	}
	
	
	
	public static List<UserDto> generateUserDtoList(List<User> userList){
		return userList.stream().map(from -> 
		                         new UserDto(from.getId(),
		                        	from.getEmail(),
		                        	from.getFirstName(),
		                        	from.getLastName(),
		                        	from.getIsActive(),
		                        	 new ArrayList<UserDetailsDto>())).collect(Collectors.toList());
	}
	
	public static List<UserDetails> generateUserDetails(){
		return IntStream.range(0,5).mapToObj(i -> 
		                        new UserDetails(
		                        i+"5455727995",
				                i+"23 nisan mahallesi",
				                i+"ankara",
				                i+"türkiye",
				                i+"06000", 
				                generateUser(Long.valueOf(i)))).collect(Collectors.toList());
	}
	
	public static List<UserDetailsDto> generateUserDetailsDtoList(List<UserDetailsDto> userList){
		return userList.stream().map(from -> 
		                         new UserDetailsDto(from.getPhoneNumber(),
		                        	from.getAddress(),
		                        	from.getCity(),
		                        	from.getCountry(),
		                        	from.getPostCode())).collect(Collectors.toList());
	}
	
	public static User generateUser(Long id) {
		return new User(id,
				"coskun.altinsoy@gmail.com",
                "firstName",
                "lastName",
                true);
	}
	
	public static UserDto generateUserDto(Long id) {
		return new UserDto(id,
				"coskun.altinsoy@gmail.com",
                "firstName",
                "lastName",
                true,
                new ArrayList<UserDetailsDto>());
	}
	
	public static UserDetails generateUserDetails(Long id) {
		return new UserDetails(id,
				"5455727995",
                "23 nisan mahallesi",
                "ankara",
                "türkiye",
                "06000",
                new User());
	}
	
	public static UserDetailsDto generateUserDetailsDto(Long id) {
		return new UserDetailsDto(
				"5455727995",
                "23 nisan mahallesi",
                "ankara",
                "türkiye",
                "06000");
	}
}
