package webg6.g55301.tasks.model;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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
@EqualsAndHashCode(exclude = "tasks")
@ToString(exclude = "tasks")
public class Student {
    @Id
    private Long number;
    @NotBlank(message = "can't be blanc")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_task", joinColumns = @JoinColumn(name = "number"), inverseJoinColumns = @JoinColumn(name = "task_id"))                                                                                                                                        // automatiquement
    @JsonIgnore
    private Set<Task> tasks;

}