package webg6.g55301.examenJanvier.repository;

import org.springframework.data.repository.CrudRepository;

import webg6.g55301.examenJanvier.model.Artist;

public interface ArtistDB  extends CrudRepository<Artist, String> {
    
}
