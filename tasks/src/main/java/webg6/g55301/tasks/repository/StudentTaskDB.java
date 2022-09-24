package webg6.g55301.tasks.repository;

import org.springframework.data.repository.CrudRepository;

import webg6.g55301.tasks.model.StudentTask;
import webg6.g55301.tasks.model.StudentTaskId;

public interface StudentTaskDB extends CrudRepository<StudentTask, StudentTaskId> {

}
