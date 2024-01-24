package com.connectglobe.config;

import java.util.Collection;
import java.util.HashSet;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.connectglobe.model.User;

public class UserLoginDetails implements UserDetails {
	
	private transient User userObj;
	
	public UserLoginDetails(User userObj) {
		super();
		this.userObj = userObj;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set =new HashSet<>();
		set.add(new SimpleGrantedAuthority(userObj.getRole()));
 		return set;
	}

	@Override
	public String getPassword() {
		
		return userObj.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userObj.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
