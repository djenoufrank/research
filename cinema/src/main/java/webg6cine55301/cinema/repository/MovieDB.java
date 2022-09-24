package webg6cine55301.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import webg6cine55301.cinema.model.AverageMovie;
import webg6cine55301.cinema.model.Movie;


public interface MovieDB extends CrudRepository<Movie,Integer>{
   @Query("Select new webg6cine55301.cinema.model.AverageMovie(m.title,avg(r.rate)) FROM Movie m right outer JOIN m.reviews r Where m.movie_id =:id GROUP BY m.title")
   public List<AverageMovie> getAVG(int id);
   }