package com.quackaboutit.equipmentapp.users.service;

import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.exceptions.UserWithEmailExists;
import com.quackaboutit.equipmentapp.users.exceptions.UserWithNameExists;
import com.quackaboutit.equipmentapp.users.repository.UserRepository;
import com.quackaboutit.equipmentapp.users.response.UserDataResponse;
import com.quackaboutit.equipmentapp.users.response.WorkerItemResponse;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final RequestRepository requestRepository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserWithNameExists();
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new UserWithEmailExists();
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username not found"));

    }

    public List<WorkerItemResponse> getWorkersList() {
        Long unitId = getCurrentUser().getUnit().getId();
        List<User> userWorkers = repository.findAllByUnitId(unitId);
        List<WorkerItemResponse> workers = new ArrayList<>();
        userWorkers.forEach(worker -> {
            Integer sentRequest = requestRepository.countSentByUser(worker.getId());
            Workplace lastWorkplace = worker.getLastWorkplace();
            workers.add(WorkerItemResponse.builder()
                    .id(worker.getId())
                    .currentWorkPlaceAddress(lastWorkplace == null ? "Не задано" : lastWorkplace.getAddress())
                    .sentRequest(sentRequest)
                    .workerName(worker.getUsername())
                    .build()
            );
        });

        return workers;
    }

    public UserDataResponse getUserData() {
        User user = getCurrentUser();
        UnitResponse unitResponse = UnitResponse.builder()
                .id(user.getUnit().getId())
                .address(user.getUnit().getAddress())
                .latitude(user.getUnit().getLatitude())
                .longitude(user.getUnit().getLongitude())
                .build();

        return UserDataResponse.builder()
                .id(user.getId())
                .name(user.getUsername())
                .unit(unitResponse)
                .build();
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return getByUsername(username);
    }
}
