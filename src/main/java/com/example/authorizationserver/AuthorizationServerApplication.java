package com.example.authorizationserver;

import com.example.authorizationserver.entities.Account;
import com.example.authorizationserver.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthorizationServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Autowired
	AccountRepository accountRepository;

	@Override
	public void run(String... args) throws Exception {
		accountRepository.deleteAll();
		accountRepository.save(new Account("user", "pass"));

		System.out.println("User added");
	}
}
