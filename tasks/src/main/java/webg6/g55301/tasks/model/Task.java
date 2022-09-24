package webg6.g55301.tasks.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "students")
@ToString(exclude = "students")
public class Task {
        @Id
        @NotBlank(message = " task must has name")
        private String task_id;
        @NotBlank(message = " no without  descript")
        private String description;
        @ManyToMany(mappedBy = "tasks")
        @JsonIgnore
        private Set<Student> students;
}