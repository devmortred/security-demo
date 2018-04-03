package com.btech.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import com.btech.spring.entities.Role;
import com.btech.spring.entities.User;
import com.btech.spring.repositories.UserRepository;

@SpringBootApplication
@EnableOAuth2Sso
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
		if(repo.count() == 0) {
			repo.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
			repo.save(new User("admin", "admin", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		}
		builder.userDetailsService (s -> new CustomUserDetails(repo.findByUsername(s)));
	}
}
