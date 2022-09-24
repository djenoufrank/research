package webg55301quiz.webg5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webg55301quiz.webg5.business.Quiz;
import webg55301quiz.webg5.model.Question;
import webg55301quiz.webg5.model.QuestionDetail;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuizREstController {
    @Autowired
    private Quiz quiz;

    @GetMapping("/question/{number}")
    public Iterable<QuestionDetail>  questionRest(@PathVariable Integer number){
        return quiz.getQuestionsRest(number);
    }
}
