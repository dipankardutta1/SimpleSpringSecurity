package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.UserDetail;
import com.example.demo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 if(username == null || username.isEmpty()) {
			 throw new UsernameNotFoundException("Username and Password is not valid");
		 }
		
		
		
		 UserDetail user = userRepository.findByUsername(username);
		 
		 if(user == null ) {
			 throw new UsernameNotFoundException("Username and Password is not valid");
		 }
		
		//GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(epfwUserMst.getRoleName()+epfwUserMst.getOtpVerified());
		
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		//list.add(grantedAuthority);
		
		UserDetails userDetails = new User(username, user.getPassword(), list);
		
		return userDetails;
		
		
		
		
		
		
	}

}
