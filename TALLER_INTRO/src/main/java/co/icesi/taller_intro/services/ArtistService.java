package co.icesi.taller_intro.services;

import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.repositories.ArtistRepository;

@Component
public class ArtistService {
    
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void save(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getAll() {
        return artistRepository.getAll();
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id);
    }

    public Artist getArtistByNameWithTracks(String name) {
        return artistRepository.findByName(name);
    }

    public void deleteArtistById(long id) {
        artistRepository.deleteById(id);
    }
}
