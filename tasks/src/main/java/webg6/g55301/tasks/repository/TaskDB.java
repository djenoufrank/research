package webg6.g55301.tasks.repository;
import org.springframework.data.repository.CrudRepository;

import webg6.g55301.tasks.model.Task;

public interface TaskDB extends CrudRepository<Task, String> {

}
