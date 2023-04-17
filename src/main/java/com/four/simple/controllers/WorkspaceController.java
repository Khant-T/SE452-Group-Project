package com.four.simple.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.four.simple.models.Workspace;
import com.four.simple.repositories.WorkspaceRepository;

@RestController
@RequestMapping("workspace")
public class WorkspaceController {


    private final WorkspaceRepository workspaceRepository;

    @Autowired
    public WorkspaceController(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @GetMapping
    public List<Workspace> getWorkspaces() {
        return workspaceRepository.findAll();
    }
    
}
