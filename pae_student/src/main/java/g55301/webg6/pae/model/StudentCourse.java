package g55301.webg6.pae.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_course")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentCourse {//ca ca sert a rien a moins qu'on veuille ajouter un attribut en plus coe ds le projet Task(janv 2021)
    @EmbeddedId
    private StudentCourseId student_course_id;
}
