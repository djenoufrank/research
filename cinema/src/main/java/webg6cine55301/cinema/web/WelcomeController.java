package webg6cine55301.cinema.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String home() {
         return "welcome";
    }
    @PostMapping("/")
    public String login() {
        return "welcome";
    }
}
