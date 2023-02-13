package com.dambroski.foodDeliveryProject.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dambroski.foodDeliveryProject.User.User;
import com.dambroski.foodDeliveryProject.User.UserRepository;

@Component
public class PwdAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User customer = repository.findUserByEmail(username);
		if(customer != null) {
			if(encoder.matches(pwd,customer.getPwd())) {

				return new UsernamePasswordAuthenticationToken(username,pwd,
						getGrantedAuthorities(customer.getAuthorities()));
			} else {
				throw new BadCredentialsException("Invalid password");
			}
		}else {
			throw new BadCredentialsException("No user registered with this details");
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Authority authority : authorities ) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
		}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
