package webg6.g55301.tasks.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTask {
    @EmbeddedId
    private StudentTaskId student_task_ids;
    private boolean fait;
}
