package g55301.webg6.pae.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class welcomeController {
    @GetMapping("/welcome")
    public String home() {
        return "welcome";
    }
     @GetMapping("/")
    public String showIndex(Model model) {
    model.addAttribute("username", "MCD");
    return "welcome";
    }

    @GetMapping("/inter")
    public String showIndex() {//pas besoin
        return "index";
    }

    @GetMapping("/translate")
    public String getInternationalPage() {//pas besoin
        return "redirect:/welcome";
    }
// ces 2 derniers ne st pas bien. un autre nommée pae test internationalisation jai bien fait la bas
}
