package web.g55301.scrum.repository;

import org.springframework.data.repository.CrudRepository;

import web.g55301.scrum.model.Story;

public interface StoryDB extends CrudRepository<Story,Integer>{
    
}
