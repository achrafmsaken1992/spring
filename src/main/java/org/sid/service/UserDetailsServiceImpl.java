package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.entities.Appuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
@Autowired
	private AccountService accountService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Appuser Appuser=accountService.findUserByEmail(email);
		//envoi exception
		if(Appuser==null) throw new UsernameNotFoundException(email);
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		Appuser.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
			
		});
		
		return new User(Appuser.getEmail(),Appuser.getPassword(),authorities);
	}

}
