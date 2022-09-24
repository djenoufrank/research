package webg6cine55301.cinema.model;
import java.util.Collection;

import javax.persistence.Entity;
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
public class Critic {
    @Id
    private String critic_name;
    @NotBlank(message = "password can't be null")
    private String password;
    private boolean enabled;
    @OneToMany(mappedBy = "critic")
    @JsonIgnore
    private Collection<Review> reviews;
}
