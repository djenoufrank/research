package webg6cine55301.cinema.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webg6cine55301.cinema.business.Cinema;
import webg6cine55301.cinema.model.AverageMovie;
import webg6cine55301.cinema.model.AvisNotFoundException;
import webg6cine55301.cinema.model.Movie;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CinemaRestController {
    @Autowired
    private Cinema cinema;
    @GetMapping("/avis/{id}")
    public ResponseEntity<List<AverageMovie>> movieRest(@PathVariable int id){
    if(id<=0){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    List<AverageMovie> it =cinema.getMoviesAVGRest(id);
    if(it.isEmpty()){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }else{
     return new ResponseEntity<List<AverageMovie>>(it, HttpStatus.OK);
    }
           
               
            
    }
    @GetMapping("/movieList")
    public Iterable<Movie> movie() {
        return cinema.getMovies();
    }

}
