package org.sid.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter{
@Autowired
	private UserDetailsService userDetailsService;
@Autowired
private BCryptPasswordEncoder  bCryptPasswordEncoder ;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*coté backend en memoire
		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("admin","user")
		.and()
		.withUser("user").password("1234").roles("user");*/
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		
		//methode de hashage
		
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//pas besoin de generer synchronise token
		http.csrf().disable()
		//desactiver la session
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		/*//coté backend
		http.formLogin();
		.loginPage("login");*/
		//tous les resources necessite authentification
		http.authorizeRequests().antMatchers("/login/**","/register/**","/").permitAll();
http.authorizeRequests().antMatchers(HttpMethod.GET,"/userL/**").hasAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}

}
