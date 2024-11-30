package com.quackaboutit.equipmentapp;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.contractor.entity.Contractor;
import com.quackaboutit.equipmentapp.contractor.repository.ContractorRepository;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import com.quackaboutit.equipmentapp.request.repository.RequestEquipmentRepository;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
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
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;
import com.quackaboutit.equipmentapp.workplace.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EquipmentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
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
    private ContractorRepository contractorRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RequestEquipmentRepository requestEquipmentRepository;

    @Bean
    public WebMvcConfigurer configureCors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("**").allowedOrigins("http://localhost:3000", "https://91eclm-79-104-7-207.ru.tuna.am", "https://tuna.am");
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

        // Adding contractors

        var contractors = new ArrayList<>();

        contractors.add(contractorRepository.save(Contractor.builder()
                .phoneNumber("8496368890")
                .inn("3984062227")
                .kpp("365921061")
                .name("Камаз International")
                .legalAddress("Щёлковское ш., 1, стр. 5")
                .build()));

        contractors.add(contractorRepository.save(Contractor.builder()
                .phoneNumber("81234567890")
                .inn("3664069397")
                .kpp("366601001")
                .name("Перевозчики")
                .legalAddress("ул. Октября, вл10, Реутов")
                .build()));

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
                .fuelType("АИ-92")
                .paymentHourly(650)
                .condition(80)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("A916AO777")
                .carBrand("LADA")
                .base(baseList.get(1))
                .fuelType("АИ-92")
                .equipmentType(equipmentTypeList.get(0))
                .paymentHourly(500)
                .condition(70)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("A526KC777")
                .carBrand("ZIL")
                .fuelType("АИ-92")
                .base(baseList.get(0))
                .equipmentType(equipmentTypeList.get(3))
                .paymentHourly(600)
                .condition(70)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("K114OO777")
                .carBrand("ZIL")
                .fuelType("АИ-92")
                .base(baseList.get(0))
                .equipmentType(equipmentTypeList.get(5))
                .paymentHourly(550)
                .condition(75)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("H743AA252")
                .carBrand("MAN")
                .fuelType("АИ-95")
                .base(baseList.get(0))
                .equipmentType(equipmentTypeList.get(5))
                .paymentHourly(750)
                .condition(90)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("С612OA177")
                .carBrand("MAN")
                .fuelType("АИ-95")
                .base(baseList.get(0))
                .equipmentType(equipmentTypeList.get(5))
                .paymentHourly(700)
                .condition(80)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("B654EE612")
                .carBrand("KAMAZ")
                .fuelType("АИ-95")
                .contractor((Contractor) contractors.get(0))
                .equipmentType(equipmentTypeList.get(3))
                .paymentHourly(600)
                .condition(50)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("B854AA642")
                .carBrand("KAMAZ")
                .fuelType("АИ-92")
                .contractor((Contractor) contractors.get(0))
                .equipmentType(equipmentTypeList.get(4))
                .paymentHourly(1000)
                .condition(95)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("B855BB642")
                .carBrand("KAMAZ")
                .fuelType("АИ-92")
                .contractor((Contractor) contractors.get(1))
                .equipmentType(equipmentTypeList.get(4))
                .paymentHourly(900)
                .condition(90)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("E169AE777")
                .carBrand("ZIL")
                .fuelType("АИ-92")
                .base(baseList.get(1))
                .equipmentType(equipmentTypeList.get(4))
                .paymentHourly(1500)
                .condition(90)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        namedEquipmentRequest = NamedEquipment.builder()
                .licensePlate("T092AY777")
                .carBrand("LADA")
                .fuelType("АИ-92")
                .base(baseList.get(1))
                .equipmentType(equipmentTypeList.get(7))
                .paymentHourly(300)
                .condition(40)
                .build();

        namedEquipmentList.add(namedEquipmentRepository.save(namedEquipmentRequest));

        // Adding workplaces

        var workplacesList = new ArrayList<>();

        var workplaceRequest = Workplace.builder()
                .address("Молокозаводская улица, 66, Арзамас, Нижегородская область")
                .latitude(55.413458)
                .longitude(43.806453)
                .state(WorkplaceState.IDLE)
                .unit(unitList.get(0))
                .build();

        workplacesList.add(workplaceRepository.save(workplaceRequest));

        workplaceRequest = Workplace.builder()
                .address("деревня Чепелёво, 71, городской округ Чехов, Московская область")
                .latitude(55.182891)
                .longitude(37.493398)
                .unit(unitList.get(1))
                .state(WorkplaceState.IDLE)
                .build();

        workplacesList.add(workplaceRepository.save(workplaceRequest));

        // Adding Contractors

        var contractor = Contractor.builder()
                .name("Возим вам")
                .inn("482426332280")
                .kpp("770201001")
                .legalAddress("630136, Новосибирская область, г. Новосибирск, ул. Троллейная, д. 19, оф. 203")
                .phoneNumber("89572917439")
                .build();

        contractorRepository.save(contractor);

        // adding requests

//        var requests = new ArrayList<>();
//        var requestedEquipment = new ArrayList<RequestedEquipment>();
//        requestedEquipment.add(requestEquipmentRepository.save(RequestedEquipment.builder()
//                .equipment(equipmentList.get(1))
//                .equipmentType(equipmentTypeList.get(2))
//                .workDuration(Duration.ofHours(4).plusMinutes(20))
//                .arrivalTime(LocalDateTime.now().withHour(8).withMinute(30))
//                .build()));
//        requestedEquipment.add(requestEquipmentRepository.save(RequestedEquipment.builder()
//                .equipment(equipmentList.get(3))
//                .equipmentType(equipmentTypeList.get(5))
//                .workDuration(Duration.ofHours(2).plusMinutes(30))
//                .arrivalTime(LocalDateTime.now().withHour(8).withMinute(30))
//                .build()));
//
//        requests.add(requestRepository.save(Request.builder()
//                .arrivalDate(LocalDateTime.now().minusDays(2))
//                .distance(50.0)
//                .requestedEquipment(requestedEquipment)
//                .workplace((Workplace) workplacesList.get(0))
//                .unit(unitList.get(0))
//                .build()));
//
//        requestedEquipment = new ArrayList<RequestedEquipment>();
//        requestedEquipment.add(requestEquipmentRepository.save(RequestedEquipment.builder()
//                .equipment(equipmentList.get(1))
//                .equipmentType(equipmentTypeList.get(2))
//                .workDuration(Duration.ofHours(4).plusMinutes(20))
//                .arrivalTime(LocalDateTime.now().withHour(8).withMinute(30))
//                .build()));
//        requestedEquipment.add(requestEquipmentRepository.save(RequestedEquipment.builder()
//                .equipment(equipmentList.get(3))
//                .equipmentType(equipmentTypeList.get(5))
//                .workDuration(Duration.ofHours(2).plusMinutes(30))
//                .arrivalTime(LocalDateTime.now().withHour(8).withMinute(30))
//                .build()));
//
//        requests.add(requestRepository.save(Request.builder()
//                .arrivalDate(LocalDateTime.now().minusDays(1))
//                .distance(75.0)
//                .requestedEquipment(requestedEquipment)
//                .workplace((Workplace) workplacesList.get(1))
//                .unit(unitList.get(0))
//                .build()));

        // Adding users
        var manager = User.builder()
                .username("Золов Юрий Давидович")
                .email("y.d.zolov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_UNIT_MANAGER)
                .unit(unitList.get(0))
                .build();

        userRepository.save(manager);

        var dispatcherUser = User.builder()
                .username("Кругов Алексей Владимирович")
                .email("a.v.krygov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_DISPATCHER)
                .unit(unitList.get(0))
                .build();

        userRepository.save(dispatcherUser);

        var workerUser = User.builder()
                .username("Мерджбранчев Никита Сергеевич")
                .email("n.s.mergebranchev@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_WORKER)
                .unit(unitList.get(0))
                .build();

        userRepository.save(workerUser);

        var adminUser = User.builder()
                .username("Мобилков Артём Александрович")
                .email("a.a.mobilkov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .unit(unitList.get(0))
                .build();

        userRepository.save(adminUser);

        manager = User.builder()
                .username("Фигмовна Екатерина Михайловна")
                .email("e.m.figmovna@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_UNIT_MANAGER)
                .unit(unitList.get(1))
                .build();

        userRepository.save(manager);

        manager = User.builder()
                .username("ttttt")
                .email("e.m.figmovna1@gmail.com")
                .password(passwordEncoder.encode("ttttt"))
                .role(Role.ROLE_UNIT_MANAGER)
                .unit(unitList.get(1))
                .build();

        userRepository.save(manager);

        dispatcherUser = User.builder()
                .username("Ковров Евгений Артёмович")
                .email("e.a.kovrov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_DISPATCHER)
                .unit(unitList.get(1))
                .build();

        userRepository.save(dispatcherUser);

        workerUser = User.builder()
                .username("Джсов Николай Романович")
                .email("n.r.jsov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_WORKER)
                .unit(unitList.get(1))
                .build();

        userRepository.save(workerUser);

        adminUser = User.builder()
                .username("Деревов Владислав Олегович")
                .email("v.o.derevov@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.ROLE_ADMIN)
                .unit(unitList.get(1))
                .build();

        userRepository.save(adminUser);
    }
}
