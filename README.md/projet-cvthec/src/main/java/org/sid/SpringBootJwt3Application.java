package org.sid;

import java.util.stream.Stream;

import org.apache.tomcat.jni.User;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootJwt3Application implements CommandLineRunner{
	@Autowired
	
	private AccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwt3Application.class, args);
	}
	@Override
	public void run(String... arg0) throws Exception {
		
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
