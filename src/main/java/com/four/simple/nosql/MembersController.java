package com.four.simple.nosql;

import com.four.simple.workspace.Workspace;
import com.four.simple.workspace.WorkspaceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Members", description = "Everything about your Instructor")
@Log4j2
public class MembersController {
    @Autowired
    private MembersRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the Members")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Members.class))})
    public List<Members> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @PostMapping
    @Operation(summary = "Save the Member ")
    public String save(Members members) {
        members = repo.findById(members.getId()).orElse(new Members(members.getName(),members.getWorkspace_Id()));

        log.traceEntry("enter save", members);
        repo.save(members);
        log.traceExit("exit save", members);
        return members.getId();

    }

    @DeleteMapping
    @Operation(summary = "Remove the Member")
    public void remove(String id) {
        log.traceEntry("Enter remove", id);
        repo.deleteById(id);
        log.traceExit("Exit remove");
    }

}