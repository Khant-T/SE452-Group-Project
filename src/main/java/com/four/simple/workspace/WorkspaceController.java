package com.four.simple.workspace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("workspaces")
@Tag(name = "Workspace", description = "Everything about your workspaces")
@Log4j2
public class WorkspaceController {

    @Autowired
    private  WorkspaceRepository workspaceRepository;

    @GetMapping
    @Operation(summary = "Get all WorkSpaces")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all the workspaces",
                content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Workspace.class),
                    examples =   {
                        @ExampleObject(value = "{\"id\": 1, \"name\": \"Workspace A\",\"user_id\": 1}"),
                        @ExampleObject(value = "{\"id\": 2, \"name\": \"Workspace B\",\"user_id\": 1}")
                    }
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Workspaces not found"
            )
        }
    )
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
            @ApiResponse(responseCode = "200",description = "Successfully retrieved workspace with ID",content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Workspace.class),
                    examples =   {@ExampleObject(value = "{\"id\": 1, \"name\": \"John Doe\",\"userId\": 1}")}

            )),
            @ApiResponse(responseCode = "400",description = "Invalid Workspace ID",content = @io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Workspace.class),
                    examples =   {@ExampleObject(value = "{\"id\": -1, \"name\": \"John Doe\",\"userId\": 1}")}

            )),
            @ApiResponse(responseCode = "404",description = "Workspace not found")
    })
    public Workspace getWorkspaceByID(@Parameter(name = "Provide a WorkSpace ID",required = true) @PathVariable long id){

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
            @ApiResponse(responseCode = "404",description = "Workspace not found")
    })
    public void deleteWorkspace(@PathVariable long id){
        log.traceEntry("Entry Delete Workspace",id);
        workspaceRepository.deleteById(id);
        log.traceExit("Exit Delete Workspace");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
