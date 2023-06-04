package com.four.simple.checklist;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/statuses")
public class StatusService {
    @Autowired
    private StatusRepository repo;

    @GetMapping
    public ModelAndView list(Model model, HttpSession session) {
        ModelAndView mAndV = new ModelAndView("simple/statuslist");

        model.addAttribute("statuses", repo.findAll());
        if (session.getAttribute("status") == null) {
            model.addAttribute("status", new Status());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("status", session.getAttribute("status"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return mAndV;
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("status", repo.findById(id));
        return "redirect:/statuses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/statuses";
    }

    @PostMapping
    public String createWorkspace(@ModelAttribute Status status, HttpSession session) {
        if (status.getId() == 0)
            repo.save(status);
        else {

//            var editCourse = repo.findById(workspace.getId());
//            editCourse.setName(editCourse.getName());
//            editCourse.setUserId(editCourse.getUserId());
//            repo.save(editCourse);
//            session.setAttribute("workspace", null);

            Optional<Status> statusOptional1 = Optional.ofNullable(repo.findById(status.getId()));
            Status existingResource = statusOptional1.get();
            existingResource.setDescription(status.getDescription());
            existingResource.setChecklist(status.getChecklist());
            repo.save(existingResource);
            session.setAttribute("workspace", null);
        }
        return "redirect:/statuses";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Status status) {
        if (status.getId() == 0)
            repo.save(status);
        else {
            Optional<Status> statusOptional1 = Optional.ofNullable(repo.findById(status.getId()));
            Status existingResource = statusOptional1.get();
            existingResource.setDescription(status.getDescription());
            existingResource.setChecklist(status.getChecklist());
            repo.save(existingResource);
        }
        return "statuses/edit";
    }

}