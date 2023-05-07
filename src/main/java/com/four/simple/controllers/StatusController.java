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

import com.four.simple.models.Status;
import com.four.simple.repositories.StatusRepository;

@RestController
@RequestMapping(path = "statuses")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;
  
    @GetMapping
    public List<Status> getStatus(){
        return statusRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Status getStatusByID(@PathVariable long id){
        return statusRepository.findById(id);
    }

    @PostMapping
    public Status CreateStatus(@RequestBody Status status){
        return statusRepository.save(status);
    }
    
    @DeleteMapping("/{id}")
    public void DeleteStatus(@PathVariable long id){
        statusRepository.deleteById(id);
    }



}
