package webg6cine55301.cinema.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AverageMovie {
    @Id
    private String title;
    private double average;
}
