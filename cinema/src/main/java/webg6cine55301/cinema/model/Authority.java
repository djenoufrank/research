package webg6cine55301.cinema.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Authority {
    @Id
    @GeneratedValue(generator = "authority_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 50)
    private long id;
    private String username;
    private String authority;
}

