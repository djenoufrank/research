package webg6.g55301.tasks.model;
import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTaskId implements Serializable {
    private Long number;
    private String task_id;
}
