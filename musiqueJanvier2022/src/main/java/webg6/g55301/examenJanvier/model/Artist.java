package webg6.g55301.examenJanvier.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Artist {
    @Id
    @NotBlank(message = "login must be specify")
    private String login;
    @NotBlank(message = "name must be specify")
    private String name;

    @OneToMany(mappedBy = "artiste",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Track> tracks;
}
