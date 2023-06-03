package com.four.simple.webcontroller;

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

@Controller
@RequestMapping("/workspaces")
public class workspaceService {
    @Autowired
    private WorkspaceRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("workspaces", repo.findAll());
        if (session.getAttribute("workspace") == null) {
            model.addAttribute("workspace", new Workspace());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("workspace", session.getAttribute("workspace"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "workspace/workspacelist";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("workspace", repo.findById(id));
        return "redirect:/workspaces";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/workspaces";
    }

    @PostMapping
    public String createWorkspace(@ModelAttribute Workspace workspace, HttpSession session) {
        if (workspace.getId() == 0)
            repo.save(workspace);
        else {

//            var editCourse = repo.findById(workspace.getId());
//            editCourse.setName(editCourse.getName());
//            editCourse.setUserId(editCourse.getUserId());
//            repo.save(editCourse);
//            session.setAttribute("workspace", null);

            Optional<Workspace> workspaceOptional1 = Optional.ofNullable(repo.findById(workspace.getId()));
            Workspace existingResource = workspaceOptional1.get();
            existingResource.setName(workspace.getName());
            existingResource.setUserId(workspace.getUserId());
            repo.save(existingResource);
            session.setAttribute("workspace", null);
        }
        return "redirect:/workspaces";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Workspace workspace) {
        if (workspace.getId() == 0)
            repo.save(workspace);
        else {
            Optional<Workspace> workspaceOptional1 = Optional.ofNullable(repo.findById(workspace.getId()));
            Workspace existingResource = workspaceOptional1.get();
            existingResource.setName(workspace.getName());
            existingResource.setUserId(workspace.getUserId());
            repo.save(existingResource);
        }
        return "workspaces/edit";
    }

}
