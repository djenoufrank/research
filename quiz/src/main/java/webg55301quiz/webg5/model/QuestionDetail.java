package webg55301quiz.webg5.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDetail {
   @Id
    private int number;
    private String text;
    private Integer answer;
}
