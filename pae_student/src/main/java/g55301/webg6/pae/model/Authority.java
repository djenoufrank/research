package g55301.webg6.pae.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
 @Data
 public class Authority {
 @Id
 private long id;
 private String username;
 private String authority;
 }