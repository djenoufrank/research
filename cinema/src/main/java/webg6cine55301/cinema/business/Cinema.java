package webg6cine55301.cinema.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import webg6cine55301.cinema.model.AverageMovie;
import webg6cine55301.cinema.model.Critic;
import webg6cine55301.cinema.model.Movie;
import webg6cine55301.cinema.model.Review;
import webg6cine55301.cinema.repository.CriticDB;
import webg6cine55301.cinema.repository.MovieDB;
import webg6cine55301.cinema.repository.ReviewDB;

@Service
@Data
public class Cinema {
    @Autowired
    private CriticDB criticDB;
    @Autowired
    private ReviewDB reviewDB;
    @Autowired
    private MovieDB movieDB;

    public Iterable<Review> getReviewsByCriticName(String name) throws Exception {
        if(name.length()==0){
throw new Exception("you didn't give something");
        }
        return criticDB.findById(name).get().getReviews();
    }

    public List<AverageMovie> getMoviesAVGRest(int id) {
        return movieDB.getAVG(id);
    }

    public Iterable<Review> getAvis() {//c'est uniqmt lavi de l'user connecter
    //donc je devais prend getName via principal et retourner criticDB.findbyid(name).get().getReviews(). mais bon lexam est passé :D

        return reviewDB.findAll();
    }

    public Iterable<Movie> getMovies() {
        return movieDB.findAll();
    }

    public void addRate(int rate, Movie movie, String currentUserName) throws Exception {
        if(movie.equals(null) || currentUserName.length()==0){
            throw new Exception("you didn't give something or your movie is null");
        }
        Iterable<Review> reviews = getReviewsByCriticName(currentUserName);
        boolean isThere = false;
        for (Review review : reviews) {
            if (review.getMovie().equals(movie)) {
                isThere = true;
            }
        }
        if (!isThere) {
            Critic newReviewCritic = criticDB.findById(currentUserName).get();
            Review reviewAdded = new Review(rate, movie, newReviewCritic);
            reviewDB.save(reviewAdded);
        }

    }

}
