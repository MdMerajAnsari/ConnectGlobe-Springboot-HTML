package com.connectglobe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectglobe.dto.UserDto;
import com.connectglobe.model.User;
import com.connectglobe.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRepository userRepository;
	
	
	@Override
	public List<User> getAllUsers() {	
		
		return userRepository.findAll();
	}


	
	@Override
	public void saveUser(UserDto userDto) {
		User user=new  User(userDto.getId(),userDto.getUsername(),userDto.getEmail(),userDto.getAddress(),userDto.getMobileNo()
							,userDto.getPassword(),userDto.getRole());
		this.userRepository.save(user);
		
	}


	@Override
	public User getUserById(int id) {
		Optional<User> optional= userRepository.findById(id);
		User user=null;
		if(optional.isPresent())
			user=optional.get();
		else 
			throw new NullPointerException("User not found for id:: "+id );
		return user;
	}


	@Override
	public void deleteUserById(int id) {
		this.userRepository.deleteById(id);
		
	}

}
