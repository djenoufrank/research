package g55301.webg6.pae.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 public class UserForTest {
 @Id
 @Size(min = 6)
 private String login;
 @NotBlank
 private String name;
 }
