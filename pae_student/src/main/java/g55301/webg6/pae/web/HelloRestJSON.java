package g55301.webg6.pae.web;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import g55301.webg6.pae.model.Info;

@RestController
@RequestMapping(path = "/api")
public class HelloRestJSON {
    @GetMapping("/hellojson")
    public Info hello() {
        return new Info("Hello, world !", new Date());
    }
}