package webg6.g55301.examenJanvier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import webg6.g55301.examenJanvier.model.Track;

public interface TrackDB  extends CrudRepository<Track, Integer> {

    @Query("SELECT new webg6.g55301.examenJanvier.model.Track(id,title,stream,artiste) FROM Track t WHERE t.stream > :givenNumber")
    List<Track> trackForGivenNumber(Long givenNumber);
    
}
