package com.four.simple.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/subtasks")
@Tag(name = "Subtask", description = "Subtask API")
public class SubtaskController 
{
    @Autowired
    private SubtaskRepository subtaskRepository;

    @GetMapping
    @Operation(summary = "Retrieve all available subtasks")
    public List<Subtask> getSubtasks()
    {
        return subtaskRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a specific subtask based on the provided ID")
    public Subtask getTasksByID(@PathVariable long id)
    {
        return subtaskRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new subtask")
    public Subtask createTask(@RequestBody Subtask subtask)
    {
        return subtaskRepository.save(subtask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subtask based on the provided ID")
    public void deleteTask(@PathVariable long id)
    {
        subtaskRepository.deleteById(id);
    }
}
