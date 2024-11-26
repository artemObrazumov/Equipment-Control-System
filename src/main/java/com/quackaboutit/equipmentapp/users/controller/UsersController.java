package com.quackaboutit.equipmentapp.users.controller;

import com.quackaboutit.equipmentapp.users.response.UserDataResponse;
import com.quackaboutit.equipmentapp.users.response.WorkerItemResponse;
import com.quackaboutit.equipmentapp.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/workers")
    private List<WorkerItemResponse> getWorkers() {
        return userService.getWorkersList();
    }


    @GetMapping
    private UserDataResponse getUserData() {
        return userService.getUserData();
    }
    
    @GetMapping("/workers/containing/{substr}")
    private List<WorkerItemResponse> findWorkerContainsSubstr(@PathVariable String substr) {
        return userService.findByNameContaining(substr);
    }
}
