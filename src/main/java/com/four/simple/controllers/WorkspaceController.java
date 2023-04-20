package com.four.simple.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.four.simple.models.Workspace;
import com.four.simple.repositories.WorkspaceRepository;

@RestController
@RequestMapping("workspaces")
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

    @GetMapping("/{id}")
    public Workspace getWorkspaceByID(@PathVariable long id){

        return workspaceRepository.findById(id);
    }

    @PostMapping
    public Workspace createWorkspace(@RequestBody Workspace workspace){
        return workspaceRepository.save(workspace);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkspace(@PathVariable long id){
        workspaceRepository.deleteById(id);
    }


    


    
}
