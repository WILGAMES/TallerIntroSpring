package co.icesi.taller_intro.services;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.ArtistRepository;
import co.icesi.taller_intro.repositories.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.midi.Track;
import java.util.List;
import java.util.Objects;
@Component
public class TracksServices {

    @Autowired
    private TracksRepository tracksRepository;
    @Autowired
    private ArtistRepository artistRepository;


    public TracksServices(TracksRepository tracksRepository, ArtistRepository artistRepository) {
        this.tracksRepository = tracksRepository;
        this.artistRepository = artistRepository;
    }

    public void save(Tracks track) {
        tracksRepository.save(track);
    }

    public void delete(long id) {
        tracksRepository.delete(id);
    }

    public List<Tracks> findAll() {
        return tracksRepository.findAll();
    }

    public List<Tracks> findByArtist(Artist artist) {
        for(Artist a : artistRepository.findAll()) {
            if(a.getId() == artist.getId()) {
                return tracksRepository.findAll();
            }
        }
        return null;
    }

    public Tracks findByTrack(Tracks track) {
        for(Tracks t : tracksRepository.findAll()) {
            if(Objects.equals(t.getTitle(), track.getTitle())){
                String title = t.getTitle();
                return tracksRepository.findByTitle(title);
            }else{
                return null;
            }
        }
        return null;
    }
}
