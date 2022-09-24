package webg55301quiz.webg5.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webg55301quiz.webg5.business.Quiz;
import webg55301quiz.webg5.model.Answer;

@Controller
public class QuizController {
    @Autowired
    private Quiz quiz;

    @GetMapping("/question")
    public String home(Model model) {
        model.addAttribute("questions", quiz.getQuestions());
        return "question";
    }
    @GetMapping("/detailQuestion")
    public String detail(@RequestParam(name = "q") Integer question,Model model){
        model.addAttribute("question",quiz.getQText(question));
        model.addAttribute("answers",quiz.get_answers(question));
        return "detailQuestion";
    }
    @PostMapping("/detailQuestion")
    public String detailForm(
        @RequestParam(value = "checkboxName",required = false) String choice,
        @RequestParam(name = "q") Integer question,Model model){
       Answer answer=null;
       if(choice != null){
            answer =new Answer( true, LocalDate.now(), quiz.getQuestionById(question));
        }else{
            answer =new Answer( false, LocalDate.now(), quiz.getQuestionById(question));
           }            
    quiz.save(answer);
        model.addAttribute("question",quiz.getQText(question));
        model.addAttribute("answers",quiz.get_answers(question));
        return "detailQuestion";
    }
}
