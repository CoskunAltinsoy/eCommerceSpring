package com.eCommerce.eCommerce.dto.userDtos;

import java.io.Serializable;
import java.util.List;

import com.eCommerce.eCommerce.dto.userDetailsDtos.UserDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable{
	private Long id;
	private String email;
    private String firstName;
	private String lastName;
	private boolean isActive;
	private List<UserDetailsDto> userDetailsDto;
}
