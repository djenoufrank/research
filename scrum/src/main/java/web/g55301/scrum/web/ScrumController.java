package web.g55301.scrum.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.g55301.scrum.business.Scrum;
import web.g55301.scrum.model.Story;

@Controller
public class ScrumController {
    @Autowired
    private Scrum scrum;

    @GetMapping("/projects")
    public String projectsList(Model model) {
        model.addAttribute("projects", scrum.getProjects());
        return "projects";
    }

    @GetMapping("/projectDetail")
    public String showProjectDetail(@RequestParam(name = "project") String projectName,Model model){
        model.addAttribute("projectName",projectName);
        model.addAttribute("sprints",scrum.get_project_detail(projectName));
        return "projectDetail";
    }

    @GetMapping("projects/projectDetail/addStory")
    public String addStoryProject(@RequestParam(name = "project") String projectName,Model model){
        model.addAttribute("newStory",new Story());
        model.addAttribute("projectName",projectName);
        return "formForStory";
    }

    @PostMapping("/projectDetail")
    public String showUpdateProjectDetail(@Valid @ModelAttribute(name="newStory") Story newStory,Model model,
            Errors error,@RequestParam(name = "project") String projectName){

        if(error.hasErrors()){
            model.addAttribute("newStory",new Story());
            return "formForStory";
        }
        scrum.add_story_project(projectName,newStory);
        model.addAttribute("newStory",new Story());
        model.addAttribute("projectName",projectName);
        model.addAttribute("sprints",scrum.get_project_detail(projectName));
        return "projectDetail";
    }
}
