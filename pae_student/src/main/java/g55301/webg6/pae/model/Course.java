package g55301.webg6.pae.model;

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
@EqualsAndHashCode(exclude = "students") // soit on fait ca, soit alors on redefini nous mm le equal et hascode et
                                         // toString en enlevant @Data sur la classe
@ToString(exclude = "students")//lombok.tostring.exclude (surtt pour gerer les dates)
public class Course {
        @Id
        @NotBlank(message = " where is sigle of your course? :)")
        private String course_id;
        @NotBlank(message = " no without title")
        private String title;
        @Positive(message = "course cannot be under 0.")
        private int credits;
        @ManyToMany(mappedBy = "courses") // courses c'est l'attribut courses qui est ds student
        @JsonIgnore
        private Set<Student> students;

        public Course(String course_id, String title, int credits) {//ce constructeur sert a rien.
                this.course_id = course_id;
                this.title = title;
                this.credits = credits;
        }

}
