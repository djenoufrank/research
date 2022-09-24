package webg55301quiz.webg5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import webg55301quiz.webg5.model.Question;
import webg55301quiz.webg5.model.QuestionDetail;

public interface QuestionDB extends CrudRepository<Question,Integer>{
    @Query("SELECT new webg55301quiz.webg5.model.QuestionDetail(number,text,SIZE(q.answers)) FROM Question q WHERE SIZE(q.answers) > :number")
    Iterable<QuestionDetail> findQuestionsRest(Integer number);
    
}
