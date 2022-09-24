package webg6.g55301.examenJanvier.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webg6.g55301.examenJanvier.business.Musique;
import webg6.g55301.examenJanvier.model.Track;

@RestController
@RequestMapping(path = "/api")
public class restService {

    @Autowired
    private Musique musiq;

     @GetMapping("/nbrStream/{givenNumber}")
    public List<Track> findTrack(@PathVariable Long givenNumber) {
        return musiq.trackForGivenNumber(givenNumber);
    } 




}
