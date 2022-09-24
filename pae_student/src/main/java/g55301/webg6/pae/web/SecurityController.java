package g55301.webg6.pae.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("back")
    public String home() {
        return "homesecurity";
    }

    @GetMapping("/private")
    public String prive() {
        return "private";
    }

    @GetMapping("/login")
    public String login() {
        return "homesecurity";// si mot de passe refuser on le renvoi vers notre page qui a le formulaire
    }
}