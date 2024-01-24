package com.connectglobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connectglobe.model.User;

@Repository
public interface UserLoginRepository extends JpaRepository<User,Integer> {
	public User findByEmail(String email);
	
}
