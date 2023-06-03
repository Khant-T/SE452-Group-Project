package com.four.simple.webcontroller;

import com.four.simple.task.Task;
import com.four.simple.task.TaskRepository;
import com.four.simple.workspace.Workspace;
import com.four.simple.workspace.WorkspaceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.four.simple.workspace.Workspace;
import com.four.simple.workspace.WorkspaceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class tasksService {

    @Autowired
    private TaskRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("tasks", repo.findAll());
        if (session.getAttribute("task") == null) {
            model.addAttribute("task", new Task());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("task", session.getAttribute("task"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "workspaces/tasklist";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("task", repo.findById(id));
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/tasks";
    }

    @PostMapping
    public String createWorkspace(@ModelAttribute Task task, HttpSession session) {
        if (task.getId() == 0)
            repo.save(task);
        else {

//            var editCourse = repo.findById(workspace.getId());
//            editCourse.setName(editCourse.getName());
//            editCourse.setUserId(editCourse.getUserId());
//            repo.save(editCourse);
//            session.setAttribute("workspace", null);

            Optional<Task> taskOptional1 = Optional.ofNullable(repo.findById(task.getId()));
            Task existingResource = taskOptional1.get();
            existingResource.setDescription(task.getDescription());
            existingResource.setStatus(task.getStatus());
            repo.save(existingResource);
            session.setAttribute("task", null);
        }
        return "redirect:/tasks";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Task task) {
        if (task.getId() == 0)
            repo.save(task);
        else {
            Optional<Task> taskOptional1 = Optional.ofNullable(repo.findById(task.getId()));
            Task existingResource = taskOptional1.get();
            existingResource.setDescription(task.getDescription());
            existingResource.setStatus(task.getStatus());
            repo.save(existingResource);
        }
        return "tasks/edit";
    }

}
