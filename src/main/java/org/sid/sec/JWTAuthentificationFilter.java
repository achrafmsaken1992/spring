package org.sid.sec;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.sid.entities.Appuser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;

	
	
	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Appuser compte;
		
		
	
		try {
			compte=new ObjectMapper().readValue(request.getInputStream(), Appuser.class);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(compte.getEmail(), compte.getPassword()));
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		User springUser=(User) authResult.getPrincipal();
		ZonedDateTime expirationTimeUtc=ZonedDateTime.now(ZoneOffset.UTC).plus(SecurityConstant.EXPIRATION_TIME,ChronoUnit.MILLIS);
		String jwt = Jwts
	            .builder()
	            .setSubject(springUser.getUsername())
	            .setExpiration(new Date(System.currentTimeMillis()+SecurityConstant.EXPIRATION_TIME))
	            //algorithme HS512
	            .signWith(SignatureAlgorithm.HS256,SecurityConstant.SECRET)
	            .claim("roles", springUser.getAuthorities())
	            .compact();
		response.addHeader(SecurityConstant.HEADER_STRING,SecurityConstant.TOKEN_PREFIX+jwt);
	//	super.successfulAuthentication(request, response, chain, authResult);
	}

	
	
	
}
