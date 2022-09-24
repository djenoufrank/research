package g55301.webg6.pae.model;

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
@EqualsAndHashCode(exclude = "courses")
@ToString(exclude = "courses")
public class Student {
    @Id
    @GeneratedValue(generator = "my_gen", strategy = GenerationType.IDENTITY)
    private int student_id;
    @NotBlank
    private String name;

    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Convert(converter = SectionConverter.class)
    private Section section;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id")) // on
                                                                                                                                              // defini
                                                                                                                                              // ns
                                                                                                                                              // meme                                                                                                                                         // automatiquement
    @JsonIgnore
    private Set<Course> courses;// le set permet de faire un distinct sur la liste retournée(evite les doublons)

    /*
     * public Student(String name, Gender gender, Section section) {// un
     * constructeur sans besoin d'id car l'id est // generer auto
     * par @GeneratedValue
     * this.name = name;
     * this.gender = gender;
     * this.section = section;
     * }
     */
}