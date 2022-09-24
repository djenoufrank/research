package webg6.g55301.tasks.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import webg6.g55301.tasks.business.TaskStudent;

@Controller
public class TaskController {
    @Autowired
    private TaskStudent taskStud;
    @GetMapping("/task")
    public String getArtsistes(Model model) {
        model.addAttribute("tasks", taskStud.getTasks());
        model.addAttribute("students", taskStud.getStudents());
        model.addAttribute("studentsTask", taskStud.getStudentsTasks());
        return "task";
    }
}
