package com.example.Note.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Note.domain.User;
import com.example.Note.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	private final UserRepository URepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository URepository) {
		this.URepository = URepository;
	}
	
	@Override
	public UserDetails loadUserByUsername (String username)	throws UsernameNotFoundException{
		
		User  currentUser = URepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username,currentUser.getPassword()
				,AuthorityUtils.createAuthorityList(currentUser.getRole()));
		return user;
	}
	
}
