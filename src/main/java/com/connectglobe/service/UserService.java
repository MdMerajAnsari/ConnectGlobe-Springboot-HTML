package com.connectglobe.service;

import java.util.List;

import com.connectglobe.dto.UserDto;
import com.connectglobe.model.User;

public interface UserService  {
	List<User> getAllUsers();
	void saveUser(UserDto user);
	User getUserById(int id);
	void deleteUserById(int id);

}
