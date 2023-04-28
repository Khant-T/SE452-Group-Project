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

import com.four.simple.models.Subtask;
import com.four.simple.repositories.SubtaskRepository;

@RestController
@RequestMapping(path = "subtasks")
public class SubTaskController {
  
    private final SubtaskRepository subtaskRepository;

    @Autowired
    public SubTaskController(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }
    @GetMapping
    public List<Subtask> getSubtasks() {
        return subtaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subtask getSubtaskByID(@PathVariable long id){

        return subtaskRepository.findById(id);
    }

    @PostMapping
    public Subtask createSubTask(@RequestBody Subtask task){
        return subtaskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteSubtask(@PathVariable long id){
        subtaskRepository.deleteById(id);
    }

}
