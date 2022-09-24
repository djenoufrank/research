package web.g55301.scrum.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.g55301.scrum.business.Scrum;
import web.g55301.scrum.dto.ProjectDetail;
import web.g55301.scrum.model.Project;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SrcumRestController {
    @Autowired
    private Scrum scrum;

    @GetMapping("/projects")
    public Iterable<Project>  serviceDB(){
        return scrum.getProjects();
    }

 /*    @GetMapping("/project/{givenProjectName}")
    public List<ProjectDetail> findProjectLike(@PathVariable String givenProjectName) {
        return scrum.get_inProgress_project(givenProjectName);
        
    } */
}
