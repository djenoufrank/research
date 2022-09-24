package webg55301quiz.webg5.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "answers")
public class Question {
    @Id
    @GeneratedValue(generator = "question_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "question_seq", sequenceName = "question_seq", allocationSize = 100)
    private int number;    
    @NotBlank(message = " your text is empty")
    private String text;
    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private Collection<Answer> answers;
}
