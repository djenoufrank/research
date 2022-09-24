package web.g55301.scrum.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
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
public class Project {
    @Id
    @NotBlank
    private String name;
    private boolean active;

    @OneToMany(mappedBy = "project")//projet c'est l'attribut de sprint 
    @JsonIgnore
    private Collection<Sprint> sprints;
}
