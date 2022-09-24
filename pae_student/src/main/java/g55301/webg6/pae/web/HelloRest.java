package g55301.webg6.pae.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*")//L’annotation @CrossOrigin permet de faire appel au service à partir d’une machine d’un autre domaine.
@RequestMapping("/api")
public class HelloRest {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String name) {
        if ("mcd".equals(name)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);// http://localhost:8080/api/hello?name=mcd ne passe
                                                                    // pas, erreur 404
        } else {
            return new ResponseEntity<>("Hello, " + name + " !", HttpStatus.OK);// http://localhost:8080/api/hello?name=ufoejz
                                                                                // passe
        }
    }

    @GetMapping("/hello2/{name}")
    public String hello2(@PathVariable("name") String name) {
        return "Bonjour, " + name;
    }

    @PostMapping("/hello")
    public String helloPost(String name) {
        return "Hello, " + name + " !";
    }

    @GetMapping("/**")
    public ModelAndView hello3() {
        return new ModelAndView("welcome");
    }
}
