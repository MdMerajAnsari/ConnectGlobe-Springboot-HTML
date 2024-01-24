package com.connectglobe.config;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.connectglobe.repository.UserRepository;
import com.connectglobe.model.User;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
public class UserLoginDetailsService implements UserDetailsService {
	private static final Logger logger = LogManager.getLogger(UserLoginDetailsService.class);

	@Autowired
	private  UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		BasicConfigurator.configure();
		try {
			
			User user =userRepository.findByEmail(email);
			if(user==null)
			{
				
				logger.info("User not found ");
				throw new UsernameNotFoundException("No User");
				
			}
			else { 
				
				logger.info("User found ");
				return new UserLoginDetails(user);
			}
		}
		catch(Exception e)
		{
			logger.info(e);
		}
		
		return null;
	}

}
