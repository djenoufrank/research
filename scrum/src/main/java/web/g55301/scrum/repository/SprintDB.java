package web.g55301.scrum.repository;

import org.springframework.data.repository.CrudRepository;

import web.g55301.scrum.model.Sprint;

public interface SprintDB extends CrudRepository<Sprint,Integer>{
    
}
