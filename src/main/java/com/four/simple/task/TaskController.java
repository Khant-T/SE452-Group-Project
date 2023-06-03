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
@RequestMapping(path = "api/tasks")
@Tag(name = "Task", description = "Task API")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    @Operation(summary = "Retrieve all available tasks")
    public List<Task> getTasks()
    {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a specific task based on the provided ID")
    public Task getTasksByID(@PathVariable long id)
    {
        return taskRepository.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    public Task createTask(@RequestBody Task task)
    {
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task based on the provided ID")
    public void deleteTask(@PathVariable long id)
    {
        taskRepository.deleteById(id);
    }

}

