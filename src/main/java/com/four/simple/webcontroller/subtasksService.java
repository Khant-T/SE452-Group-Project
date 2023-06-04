package com.four.simple.webcontroller;

import com.four.simple.task.Subtask;
import com.four.simple.task.SubtaskRepository;
import com.four.simple.workspace.Workspace;
import com.four.simple.workspace.WorkspaceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/subtasks")
public class subtasksService {
    @Autowired
    private SubtaskRepository repo;

    @GetMapping
    public ModelAndView list(Model model, HttpSession session) {

        ModelAndView mAndV = new ModelAndView("simple/subtasklist");

        model.addAttribute("subtasks", repo.findAll());
        if (session.getAttribute("subtask") == null) {
            model.addAttribute("subtask", new Subtask());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("subtask", session.getAttribute("subtask"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return mAndV;
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("subtask", repo.findById(id));
        return "redirect:/subtasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/subtasks";
    }

    @PostMapping
    public String createWorkspace(@ModelAttribute Subtask subtask, HttpSession session) {
        if (subtask.getId() == 0)
            repo.save(subtask);
        else {

//            var editCourse = repo.findById(workspace.getId());
//            editCourse.setName(editCourse.getName());
//            editCourse.setUserId(editCourse.getUserId());
//            repo.save(editCourse);
//            session.setAttribute("workspace", null);

            Optional<Subtask> workspaceOptional1 = Optional.ofNullable(repo.findById(subtask.getId()));
            Subtask existingResource = workspaceOptional1.get();
            existingResource.setDescription(subtask.getDescription());
            existingResource.setCompleted(subtask.getCompleted());
            repo.save(existingResource);
            session.setAttribute("subtask", null);
        }
        return "redirect:/subtasks";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Subtask subtask) {
        if (subtask.getId() == 0)
            repo.save(subtask);
        else {
            Optional<Subtask> workspaceOptional1 = Optional.ofNullable(repo.findById(subtask.getId()));
            Subtask existingResource = workspaceOptional1.get();
            existingResource.setDescription(subtask.getDescription());
            existingResource.setCompleted(subtask.getCompleted());
            repo.save(existingResource);
        }
        return "subtasks/edit";
    }

}