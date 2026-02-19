package co.icesi.taller_intro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.TracksRepository;

@Component
public class TracksServices {

    public TracksServices() {
    }

    public void save(Tracks track) {
        TracksRepository.save(track);
    }

    public void delete(long id) {
        TracksRepository.deleteById(id);
    }

    public List<Tracks> findAll() {
        return TracksRepository.findAll();
    }

    public List<Tracks> findByArtist(Artist artist) {
        if (artist == null) {
            return new ArrayList<>();
        }

        return TracksRepository.findAll().stream()
                .filter(t -> t.getArtists() != null && t.getArtists().stream().anyMatch(a -> a.getId() == artist.getId()))
                .collect(Collectors.toList());
    }

    public Tracks findByTrack(Tracks track) {
        if (track == null || track.getTitle() == null) return null;
        return TracksRepository.findByTitle(track.getTitle());
    }
}
