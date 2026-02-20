package co.icesi.taller_intro.services;

import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.TracksRepository;

@Component
public class TracksServices {

    private final TracksRepository tracksRepository;
    private final ArtistService artistService;

    public TracksServices(TracksRepository tracksRepository, ArtistService artistService) {
        this.tracksRepository = tracksRepository;
        this.artistService = artistService;
    }

    public void save(Tracks track) {
        tracksRepository.save(track);
    }


    public void delete(long id) {
        tracksRepository.deleteById(id);
    }

    public List<Tracks> findAll() {
        return tracksRepository.findAll();
    }


}
