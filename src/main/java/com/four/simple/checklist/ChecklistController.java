package com.four.simple.checklist;

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
@RequestMapping(path = "api/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping
    public List<Checklist> getChecklists(){
        return checklistRepository.findAll();
    }

    @GetMapping("/{id}")
    public Checklist getChecklistByID(@PathVariable long id){
        return checklistRepository.findById(id);
    }

    @PostMapping
    public Checklist CreateChecklist(@RequestBody Checklist checklist){
        return checklistRepository.save(checklist);
    }

    @DeleteMapping("/{id}")
    public void DeleteChecklist(@PathVariable long id){
        checklistRepository.deleteById(id);
    }



}
