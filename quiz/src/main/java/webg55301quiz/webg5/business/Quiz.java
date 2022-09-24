package webg55301quiz.webg5.business;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import webg55301quiz.webg5.model.Answer;
import webg55301quiz.webg5.model.Question;
import webg55301quiz.webg5.model.QuestionDetail;
import webg55301quiz.webg5.repository.AnswerDB;
import webg55301quiz.webg5.repository.QuestionDB;


@Service 
@Data
public class Quiz {
    @Autowired
    private AnswerDB answerDB;
    @Autowired
    private QuestionDB questionDB;
    public Iterable<Question> getQuestions() {
        return questionDB.findAll();
    }
    public String getQText(Integer question) {
        return questionDB.findById(question).get().getText();
    }
    public Iterable<Answer> get_answers(Integer question) {
        return questionDB.findById(question).get().getAnswers();
    }
    public Iterable<QuestionDetail> getQuestionsRest(Integer number) {
        return questionDB.findQuestionsRest(number);
    }
    public Question getQuestionById(Integer question) {
        return questionDB.findById(question).get();
    }
    public void save(@Valid Answer answer) {
      answerDB.save(answer);
    }
}
