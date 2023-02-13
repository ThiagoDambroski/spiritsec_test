package com.dambroski.foodDeliveryProject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.User.UserRepository;


@Service
public class foodUserDetails implements UserDetailsService{
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String userName,password = null;
		List<GrantedAuthority> authorites = null;
		com.dambroski.foodDeliveryProject.User.User customer = repository.findUserByEmail(username);
		if(customer == null) {
			throw new UsernameNotFoundException("User details no found");
		}else {
			userName = customer.getEmail();
			password = customer.getPwd();
			authorites = new ArrayList<>();
			authorites.add(new SimpleGrantedAuthority(customer.getRole()));
		}
		return new User(userName,password,authorites);// this user is from spring security


	}

	

}
