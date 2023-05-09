package com.four.simple.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.four.simple.models.Workspace;
import com.four.simple.repositories.WorkspaceRepository;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("workspaces")
@Log4j2
public class WorkspaceController {

    @Autowired
    private  WorkspaceRepository workspaceRepository;

    @GetMapping
    @Operation(summary = "Get all WorkSpaces")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved all the workspaces",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Workspace.class,
                            example = "{\"id\": 1, \"name\": \"John Doe\",\"userId\": 1}"))),
            @ApiResponse(responseCode = "400",description = "Invalid URL"),
            @ApiResponse(responseCode = "404",description = "Workspaces not found")
    })

    public List<Workspace> getWorkspaces() {

        log.traceEntry("WorkSpace List");
        var workspaceList = workspaceRepository.findAll();
        if(!workspaceList.isEmpty()) {
            log.traceExit("Exit Workspace", workspaceList);
            return workspaceRepository.findAll();
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No workspaces found");

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get WorkSpaces with ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved workspace with ID"),
            @ApiResponse(responseCode = "400",description = "Invalid URL"),
            @ApiResponse(responseCode = "404",description = "Workspace not found")
    })
    public Workspace getWorkspaceByID(@PathVariable long id){

        log.traceEntry("Entry Workspace with ID");
        var workspaceWithId = workspaceRepository.findById(id);
        log.traceExit("Exit Workspace with ID",workspaceWithId);

        return workspaceRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create WorkSpace")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully created workspace"),
            @ApiResponse(responseCode = "400",description = "Invalid URL"),
            @ApiResponse(responseCode = "404",description = "Workspace not found")
    })
    public void createWorkspace(@RequestBody Workspace workspace){
        log.traceEntry("Entry Create Workspace",workspace);
        workspaceRepository.save(workspace);
        log.traceExit("Exit Create Workspace",workspace);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Workspace")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully deleted workspace with ID"),
            @ApiResponse(responseCode = "400",description = "Invalid URL"),
            @ApiResponse(responseCode = "404",description = "Workspace not found")
    })
    public void deleteWorkspace(@PathVariable long id){
        log.traceEntry("Entry Delete Workspace",id);
        workspaceRepository.deleteById(id);
        log.traceExit("Exit Delete Workspace");
    }
    
}
