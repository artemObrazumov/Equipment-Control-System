package com.quackaboutit.equipmentapp;

import com.quackaboutit.equipmentapp.users.entity.Role;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EquipmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		var user = User.builder()
				.username("Artem")
				.email("test@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_ADMIN)
				.build();

		userService.create(user);
	}
}
