package co.icesi.taller_intro.services;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.repositories.ArtistRepository;
import co.icesi.taller_intro.repositories.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private TracksRepository tracksRepository;

    public ArtistService(ArtistRepository artistRepository, TracksRepository tracksRepository) {
        this.artistRepository = artistRepository;
        this.tracksRepository = tracksRepository;
    }

    public void save(Artist artist) {
        artistRepository.save(artist);
    }

    public void findById(long id) {
        Artist artist = artistRepository.findById(id);
    }

    public List<Artist> getAll() {
        return artistRepository.findAll();

    }
}
