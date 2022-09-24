package webg6.g55301.examenJanvier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Track {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank(message = "title can't be empty")
    private String title;
    @Positive(message = "stream number must be positive")
    private Long stream;

    @ManyToOne(optional = false)
    @JoinColumn(name = "login")
    private Artist artiste;
}
