package com.quackaboutit.equipmentapp;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import com.quackaboutit.equipmentapp.unit.service.UnitService;
import com.quackaboutit.equipmentapp.users.entity.Role;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.repository.UserRepository;
import com.quackaboutit.equipmentapp.users.service.UserService;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceRequest;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;
import com.quackaboutit.equipmentapp.workplace.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
public class EquipmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private BaseRepository baseRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Autowired
	private EquipmentTypeRepository equipmentTypeRepository;

	@Autowired
	private NamedEquipmentRepository namedEquipmentRepository;

	@Autowired
	private WorkplaceRepository workplaceRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public WebMvcConfigurer configureCors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("**").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Override
	public void run(String... args) {

		// Adding equipment
		var equipmentList = new ArrayList<Equipment>();
		var equipmentTypeList = new ArrayList<EquipmentType>();
		var equipmentRequest = Equipment.builder()
				.name("Автоцистерна")
				.image("https://sun9-1.userapi.com/impg/qVwmVZL-TNUcEVzRHUHVTaTcQSMlvr2b7UKG9A/_BKuikk8ngE.jpg?size=343x157&quality=95&sign=66558f63eb7118ab850a07545c71be1e&type=album")
				.build();

		equipmentList.add(equipmentRepository.save(equipmentRequest));

		var equipmentTypeRequest = EquipmentType.builder()
				.type("5 тонн")
				.equipment(equipmentList.getLast())
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.type("8 тонн")
				.equipment(equipmentList.getLast())
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentRequest = Equipment.builder()
				.name("Подъёмные агрегаты")
				.image("https://sun9-16.userapi.com/impg/m1FtE45hMgJ3vlEnLXb-aRIibI_-BXdKGVUR5A/1dUGJrcomto.jpg?size=438x267&quality=95&sign=a027a3641aea65eec1433f7d00a4a6cd&type=album")
				.build();

		equipmentList.add(equipmentRepository.save(equipmentRequest));

		// Adding units
		var unitRequest = Unit.builder()
				.address("адрес 1")
				.latitude(34.4)
				.longitude(54.6)
				.build();

		var unit = unitRepository.save(unitRequest);

		// Adding bases
		var baseRequest = Base.builder()
				.unit(unit)
				.address("test address")
				.longitude(12.4)
				.latitude(34.4)
				.build();

		var base = baseRepository.save(baseRequest);

		// Adding named equipment
		var namedEquipmentRequest = NamedEquipment.builder()
				.licensePlate("123")
				.carBrand("HAVAL")
				.base(base)
				.equipmentType(equipmentTypeList.getFirst())
				.build();

		var namedEq = namedEquipmentRepository.save(namedEquipmentRequest);

		// Adding workplaces
		var workplaceRequest = Workplace.builder()
				.address("address 1")
				.latitude(12.9)
				.longitude(44.5)
				.build();

		workplaceRepository.save(workplaceRequest);

		// Adding users
		var manager = User.builder()
				.username("Manager")
				.email("manager@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_UNIT_MANAGER)
				.unit(unit)
				.build();

		userRepository.save(manager);

		var dispatcherUser = User.builder()
				.username("Dispatcher")
				.email("dispatcher@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_DISPATCHER)
				.build();

		userRepository.save(dispatcherUser);

		var adminUser = User.builder()
				.username("Admin")
				.email("admin@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_ADMIN)
				.build();

		userRepository.save(adminUser);
	}
}
