package webg55301quiz.webg5.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import webg55301quiz.webg5.model.Answer;

public interface AnswerDB extends CrudRepository<Answer,Integer>{
    
   
    
}
