package webg55301quiz.webg5.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
 
    @Id
    @GeneratedValue(generator = "answer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "answer_seq",initialValue = 11, sequenceName = "answer_seq", allocationSize = 100)
    private int answer_id;
    private boolean agree;
    @NotNull(message = "can not be null")
    private LocalDate dateAdded;
    @ManyToOne(optional = false) // ie la classe si(answer) ne peut pas etre null ,il doit tjr etre lié a une qestion
    @JoinColumn(name = "number")
     private Question question;

     public Answer(boolean b, LocalDate now, Question questionG) {
        agree=b;
        dateAdded=now;
        question=questionG;
    }
}
