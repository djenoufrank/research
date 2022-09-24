package web.g55301.scrum.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import web.g55301.scrum.dto.ProjectDetail;
import web.g55301.scrum.model.Project;
import web.g55301.scrum.model.Sprint;
import web.g55301.scrum.model.Story;
import web.g55301.scrum.repository.ProjectDB;
import web.g55301.scrum.repository.StoryDB;

@Data
@Service
public class Scrum {
    @Autowired
    private ProjectDB projectDB;

    @Autowired
    private StoryDB storyDB;

    public List<Project> getProjects() {
        return (List<Project>) projectDB.findAll();
    }

    public Iterable<Sprint> get_project_detail(String projectId){
        return projectDB.findById(projectId).get().getSprints();
    };

    public void add_story_project(String projectId,Story newStory){

        if(projectDB.findById(projectId).get().isActive()){
             List<Sprint> listeSprints=(List<Sprint>) projectDB.findById(projectId).get().getSprints();
             if(listeSprints.size()>=1){
                 listeSprints.get(listeSprints.size()-1).getStories().add(newStory);
             }
             storyDB.save(newStory);
             projectDB.save(projectDB.findById(projectId).get());
        }
     }

   /*  public List<ProjectDetail> get_inProgress_project(String givenProjectName) {
        return projectDB.findProjectBeginLike(givenProjectName);
    }; */
}
