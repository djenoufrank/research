package web.g55301.scrum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Story {
    @Id
    @GeneratedValue(generator = "story_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "story_id", sequenceName = "story_seq"/*,initialValue =21*/, allocationSize = 100)
    //initialValue =21 car on a deja 20 story ds la db
    private int story_id;
    @NotBlank(message = " Attention, cette entrée ne peut etre vide")
    private String title;
    @Positive(message ="le nombre de crédits doit etre positif et superieur à zéro")
    private int estimate;
    @ManyToOne(optional = true)
    @JoinColumn(name = "sprint_id")//sprint_id est l'id de sprint
    @JsonIgnore
    private Sprint sprint;
}
