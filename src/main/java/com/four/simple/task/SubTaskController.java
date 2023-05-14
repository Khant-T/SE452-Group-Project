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

@RestController
@RequestMapping(path = "subtasks")
public class SubtaskController
{
    @Autowired
    private SubtaskRepository subtaskRepository;

    @GetMapping
    public List<Subtask> getSubtasks()
    {
        return subtaskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subtask getSubtaskById(@PathVariable long id)
    {
        return subtaskRepository.findById(id);
    }

    @PostMapping
    public Subtask createSubtask(@RequestBody Subtask task)
    {
        return subtaskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteSubtask(@PathVariable long id)
    {
        subtaskRepository.deleteById(id);
    }

}
