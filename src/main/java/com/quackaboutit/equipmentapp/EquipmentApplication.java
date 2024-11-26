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

		var equipmentRequest = Equipment.builder()
				.name("Автоцистерна")
				.image("https://psv4.userapi.com/s/v1/d/TnMeDLKe8DNpnHodu7hVoFepe0F0JSGx8dqGP5W-qaKwt8oNlsrE9dffex23XcHXvTVt-XTLcG8gyGJNQPj3p97J-ZY4E2KV_UvxE_zfwhvvTNjF3l4Orw/rb_54227.png")
				.build();
		
		equipmentList.add(equipmentRepository.save(equipmentRequest));

		equipmentRequest = Equipment.builder()
				.name("Дорожный каток")
				.image("https://psv4.userapi.com/s/v1/d/fPTB8Eh-m8SKl5T1ZQT5Rd4oMTe9-cfvClYfjx9AObFeHjsHqzSBO5JpsV4-W-nM3uE9C9JzQQolB0ARXJ3wOXx4TAa_KzwFWUl8TakUP2Oje5zn6E0eCw/133742270_10239040_2.png")
				.build();
		
		equipmentList.add(equipmentRepository.save(equipmentRequest));

		equipmentRequest = Equipment.builder()
				.name("Экскаватор")
				.image("https://psv4.userapi.com/s/v1/d/KSY8akU9Tbss_retxCJ7OrxSOU7sDm9TpfuaNVxVH7vBDTQqHKxX6AI3SgEsezDuA5aOlBnLbJixLcBJ_Ax6cRYXt0I8m1bep10QoL-WAuqRibjIT8rnXA/133742268_10239036_2.png")
				.build();
		
		equipmentList.add(equipmentRepository.save(equipmentRequest));

		equipmentRequest = Equipment.builder()
				.name("Бетономешалка")
				.image("https://psv4.userapi.com/s/v1/d/_uPcynQKDmKpVSTKn-TrU_teHIm9T2b0bR41cDvcetCqJJ7zVakXriK4Hprw1rEovHiLLYOKJImMOEDf64klrd08p5MXIy-9BscxvqlHb4BieDsfFuyVdQ/rb_109837_2.png")
				.build();
		
		equipmentList.add(equipmentRepository.save(equipmentRequest));

		equipmentRequest = Equipment.builder()
				.name("Подъёмный кран")
				.image("https://psv4.userapi.com/s/v1/d/cEHdesU6KEoN8vtHHkjLpnntMIcioOL5D869HHK7l61Npuhuhw_udIIR9wuPRvpiwwAVcyUCpeoHx2LYFOdJaLR_o4qxqJ1MbnPjQBasOnIY4lzdPLX9jQ/rb_60032.png")
				.build();
		
		equipmentList.add(equipmentRepository.save(equipmentRequest));

		// Adding equipment type
		var equipmentTypeList = new ArrayList<EquipmentType>();

		var equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(0))
				.type("Бензовоз 25 тыс. литров") 
				.build();
		
		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(0))
				.type("Газовоз 25 тыс. литров") 
				.build();
		
				equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(1))
				.type("Грунтовый") 
				.build();
		
		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));
		
		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(1))
				.type("Асфальтный") 
				.build();
		
		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));
		
		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(2))
				.type("Карьерный") 
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(3))
				.type("150 литров") 
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(4))
				.type("Грузоподъёмный") 
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		equipmentTypeRequest = EquipmentType.builder()
				.equipment(equipmentList.get(4))
				.type("Поворотный") 
				.build();

		equipmentTypeList.add(equipmentTypeRepository.save(equipmentTypeRequest));

		// Adding unit

		var unitList = new ArrayList<Unit>();

		var unitRequest = Unit.builder()
				.address("Московская улица, 14, Чехов, Московская область")
				.latitude(55.142401)
				.longitude(37.451761)
				.build();
		
		unitList.add(unitRepository.save(unitRequest));

		unitRequest = Unit.builder()
				.address("улица Калинина, 34А, Арзамас, Нижегородская область")
				.latitude(55.396427)
				.longitude(43.831246)
				.build();
		
		unitList.add(unitRepository.save(unitRequest));

		// Adding base

		var baseList = new ArrayList<Base>();

		var baseRequest = Base.builder()
				.address("Московская улица, 14, Чехов, Московская область")
				.latitude(55.142401)
				.longitude(37.451761)
				.unit(unitList.get(0))
				.build();
		
		baseList.add(baseRepository.save(baseRequest));

		baseRequest = Base.builder()
				.address("улица Калинина, 34А, Арзамас, Нижегородская область")
				.latitude(55.396427)
				.longitude(43.831246)
				.unit(unitList.get(1))
				.build();
		
		baseList.add(baseRepository.save(baseRequest));

		// Adding named equipment

		var namedEquipmentList = new ArrayList<NamedEquipment>();

		var namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("O726AK777")
					.carBrand("LADA")
					.base(baseList.get(0))
					.equipmentType(equipmentTypeList.get(0))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));
		
		namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("A916AO777")
					.carBrand("LADA")
					.base(baseList.get(1))
					.equipmentType(equipmentTypeList.get(0))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

		namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("A526KC777")
					.carBrand("ZIL")
					.base(baseList.get(0))
					.equipmentType(equipmentTypeList.get(3))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

		namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("K114OO777")
					.carBrand("ZIL")
					.base(baseList.get(0))
					.equipmentType(equipmentTypeList.get(5))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

		namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("E169AE777")
					.carBrand("ZIL")
					.base(baseList.get(1))
					.equipmentType(equipmentTypeList.get(4))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));
		
		namedEquipmentRequest = NamedEquipment.builder()
					.licensePlate("T092AY777")
					.carBrand("LADA")
					.base(baseList.get(1))
					.equipmentType(equipmentTypeList.get(7))
					.build();

		namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));
		
		// Adding workplaces

		var workplaceRequest = Workplace.builder()
				.address("Молокозаводская улица, 66, Арзамас, Нижегородская область")
				.latitude(55.413458)
				.longitude(43.806453)
				.build();

		workplaceRepository.save(workplaceRequest);

		workplaceRequest = Workplace.builder()
				.address("деревня Чепелёво, 71, городской округ Чехов, Московская область")
				.latitude(55.182891)
				.longitude(37.493398)
				.build();

		workplaceRepository.save(workplaceRequest);

		// Adding users
		var manager = User.builder()
				.username("Юрий Давидович Золов")
				.email("y.d.zolov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_UNIT_MANAGER)
				.unit(unitList.get(0))
				.build();

		userRepository.save(manager);

		var dispatcherUser = User.builder()
				.username("Алексей Владимирович Кругов")
				.email("a.v.krygov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_DISPATCHER)
				.unit(unitList.get(0))
				.build();

		userRepository.save(dispatcherUser);

		var workerUser = User.builder()
				.username("Никита Сергеевич Мерджбранчев")
				.email("n.s.mergebranchev@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_WORKER)
				.unit(unitList.get(0))
				.build();

		userRepository.save(workerUser);

		var adminUser = User.builder()
				.username("Артём Александрович Мобилков")
				.email("a.a.mobilkov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_ADMIN)
				.unit(unitList.get(0))
				.build();

		userRepository.save(adminUser);

		manager = User.builder()
				.username("Екатерина Михайловна Фигмовна")
				.email("e.m.figmovna@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_UNIT_MANAGER)
				.unit(unitList.get(1))
				.build();

		userRepository.save(manager);

		dispatcherUser = User.builder()
				.username("Евгений Артёмович Ковров")
				.email("e.a.kovrov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_DISPATCHER)
				.unit(unitList.get(1))
				.build();

		userRepository.save(dispatcherUser);

		workerUser = User.builder()
				.username("Николай Романович Джсов")
				.email("n.r.jsov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_WORKER)
				.unit(unitList.get(1))
				.build();

		userRepository.save(workerUser);

		adminUser = User.builder()
				.username("Владислав Олегович Деревов")
				.email("v.o.derevov@gmail.com")
				.password(passwordEncoder.encode("password"))
				.role(Role.ROLE_ADMIN)
				.unit(unitList.get(1))
				.build();

		userRepository.save(adminUser);
	}
}
