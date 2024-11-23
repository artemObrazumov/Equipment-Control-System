package com.quackaboutit.equipmentapp;

import com.quackaboutit.equipmentapp.users.entity.Role;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.service.UserService;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceRequest;
import com.quackaboutit.equipmentapp.workplace.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EquipmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private WorkPlaceService workPlaceService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {

		// Adding workplaces
		var workplace = new WorkplaceRequest(12.2, 13.3, "123");

		workPlaceService.create(workplace);
		workPlaceService.create(workplace);
		workPlaceService.create(workplace);
		workPlaceService.create(workplace);

		// Adding users
		var dispatcherUser = User.builder()
				.username("Dispatcher")
				.email("dispatcher@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_DISPATCHER)
				.build();

		userService.create(dispatcherUser);

		var adminUser = User.builder()
				.username("Admin")
				.email("admin@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_ADMIN)
				.build();

		userService.create(adminUser);
	}
}
