package web.g55301.scrum.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.JoinColumn;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sprint {
    @Id
    @GeneratedValue(generator = "sprint_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sprint_id", sequenceName = "sprint_seq", allocationSize = 100)
    private int sprint_id;
    @Positive
    private int number;
    @Positive
    private int days;
    @ManyToOne(optional = true)
    @JoinColumn(name = "name")//name est l'id de projet
    private Project project;

    
    @OneToMany(mappedBy = "sprint")//sprint est l'attribut ds story
    @JsonIgnore
    private Collection<Story> stories;
}
