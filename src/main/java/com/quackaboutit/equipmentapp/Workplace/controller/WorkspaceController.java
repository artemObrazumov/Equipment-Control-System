package com.quackaboutit.equipmentapp.workplace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    @GetMapping
    private WorkplaceResponse findWorkspaces(){
        return ;
    }
}
