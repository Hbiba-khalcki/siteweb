package com.projet.service;

import com.projet.web.dto.UserRegistrationDto;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.projet.Entity.User;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
   

    User save(UserRegistrationDto registration);

	List<User> findAll();
	Optional<User> findById(Long id);




	


}
