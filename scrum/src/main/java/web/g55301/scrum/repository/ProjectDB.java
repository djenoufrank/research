package web.g55301.scrum.repository;

import org.springframework.data.repository.CrudRepository;

import web.g55301.scrum.model.Project;

public interface ProjectDB extends CrudRepository<Project,String>{
   
    //@Query("SELECT new web.g55301.scrum.dto.ProjectDetail(name,SIZE(p.sprints),SIZE(s.stories)) FROM Project p JOIN Sprint s WHERE  p.active=true GROUP BY name")
    //public List<ProjectDetail> findProjectBeginLike(String givenProjectName);

    //@Query("select   NEW heb.webg6.scrum.dto.ProjectDto(p.name,p.sprints.size,count(st)) from Project p JOIN p.sprints s join s.stories st where p.name  like CONCAT(?1,'%') group by p.name,p.sprints.size")
    //List<ProjectDto> findProjectByNameStartingWith(String name);

}