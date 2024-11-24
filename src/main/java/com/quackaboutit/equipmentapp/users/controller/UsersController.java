package com.quackaboutit.equipmentapp.users.controller;

import com.quackaboutit.equipmentapp.users.response.WorkerItemResponse;
import com.quackaboutit.equipmentapp.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
}