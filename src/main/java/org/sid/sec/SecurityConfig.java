package org.sid.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
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
	
		http.csrf().disable()
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		

		http.authorizeRequests().antMatchers("/register/**","/login/**","/activationCompte/**","/getPhotoOffreByManager/**","/getPhotoEtudiant/**","/getPhotoManager/**","/updateTokenNotification/**","/isActiveAndValide/**","/recoveryPassword/**","/updateAuthentification/**","/envoyerContact/**","/getCvEtudiant/**").permitAll();
		
		//swagger link authorized
		http.authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
                 "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html",
                 "/swagger-resources/configuration/security")
         .permitAll();
		http.authorizeRequests().antMatchers("/etudiant/**").hasAuthority("ETUDIANT");
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/manager/**").hasAuthority("MANAGER");
		http.authorizeRequests().antMatchers("/getManagers/**").hasAnyAuthority("ETUDIANT","ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthentificationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}

}
