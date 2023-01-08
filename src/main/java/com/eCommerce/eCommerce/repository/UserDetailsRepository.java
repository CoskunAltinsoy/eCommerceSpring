package com.eCommerce.eCommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eCommerce.eCommerce.model.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{

}
