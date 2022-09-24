package webg6.g55301.examenJanvier.business;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import webg6.g55301.examenJanvier.model.Artist;
import webg6.g55301.examenJanvier.model.Track;
import webg6.g55301.examenJanvier.repository.ArtistDB;
import webg6.g55301.examenJanvier.repository.TrackDB;

@Service
@Data
public class Musique {
    @Autowired
    private ArtistDB artistDB;
    @Autowired
    private TrackDB trackDB;

    public Iterable<Artist> getArtists() {
        return  artistDB.findAll();
    }
    public List<Track> get_artist_detail(String name) throws Exception{
        if(!artistDB.findById(name).isPresent()){
            throw new Exception();// ici je dois/devrait utiliser le response entity comme jai fait sur le projet cinema
        }
        else{
        return new ArrayList<>(artistDB.findById(name).get().getTracks());
    }
    }
    public Artist getArtistById(String artistLogin) {
        return artistDB.findById(artistLogin).get();
    }
    public List<Track> trackForGivenNumber(Long givenNumber) {
        return trackDB.trackForGivenNumber(givenNumber);
    }

    //je nutilise pas la méthose addStream apparemment
    public void addStream(String artisteLogin, Integer trackOfArtist, Long streaming) {
        if (artisteLogin !=null) {
            if (artistDB.existsById(artisteLogin)) {
                Artist artist= artistDB.findById(artisteLogin).get();
                Track track= trackDB.findById(trackOfArtist).get();
                
                track.setStream(track.getStream()+streaming);
                artistDB.save(artist);
            }
        }
    }
    public void addStreamSSS(@Valid Track newTrack, String artisteLogin) {
        if (artisteLogin !=null && newTrack!=null) {
            if (artistDB.existsById(artisteLogin)) {
                Artist artist= artistDB.findById(artisteLogin).get();
                Track track= trackDB.findById(newTrack.getId()).get();
                
                track.setStream(track.getStream()+newTrack.getStream());
                trackDB.save(track);
                artistDB.save(artist);
            }
        }
    }
}
