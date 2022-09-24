package webg6cine55301.cinema.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "reviews")
public class Movie {
    @Id
    @GeneratedValue
    private int movie_id;
    @NotBlank(message = "title can't be empty")
    private String title;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private Collection<Review> reviews;
}
