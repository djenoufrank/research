package webg6.g55301.examenJanvier.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class welcomeController {
    @GetMapping("/")
    public String home() {
         return "welcome";
    }
}
