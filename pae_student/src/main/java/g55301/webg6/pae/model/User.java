package g55301.webg6.pae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
@Id
private String username;
private String password;
private boolean enabled;
}