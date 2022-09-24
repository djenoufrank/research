package webg6.g55301.tasks.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import webg6.g55301.tasks.model.Student;
import webg6.g55301.tasks.model.StudentTask;
import webg6.g55301.tasks.model.Task;
import webg6.g55301.tasks.repository.StudentDB;
import webg6.g55301.tasks.repository.StudentTaskDB;
import webg6.g55301.tasks.repository.TaskDB;

@Service 
@Data
public class TaskStudent {
    @Autowired
    private TaskDB taskDB;
    @Autowired
    private StudentDB studentDB;
    @Autowired
    private StudentTaskDB studentTaskDB;
    public List<Task> getTasks() {
        return (List<Task>) taskDB.findAll();
    }

    public List<Student> getStudents() {
        return (List<Student>) studentDB.findAll();
    }
    public List<StudentTask> getStudentsTasks() {
        return (List<StudentTask>) studentTaskDB.findAll();
    }
}
