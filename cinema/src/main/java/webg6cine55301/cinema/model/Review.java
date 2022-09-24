package webg6cine55301.cinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    @Id
    @GeneratedValue(generator = "review_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "review_id", sequenceName = "review_seq",initialValue =6, allocationSize = 50)
    private int review_id;
    @Max(value = 5,message = "shool be between 1 and 5")
    @Min(value = 0,message = "shool be between 0 and 5")
    private int rate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @ManyToOne(optional = false)
    @JoinColumn(name = "critic_name")
    private Critic critic;

    public Review(int rate,Movie movie,Critic critic){
        this.rate=rate;
        this.movie=movie;
        this.critic=critic;
    } 
}
