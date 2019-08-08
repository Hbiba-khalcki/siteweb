package com.projet.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.Entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findByEmail(String email);


	Optional<User> findById(Long id);
}
