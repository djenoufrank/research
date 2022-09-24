package web.g55301.scrum.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDetail {
    @Id
    @NotBlank
    private String name;
    private int sizeSprint;
    private int sizeStories;
}
