package co.icesi.taller_intro.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Tracks;
import java.util.ArrayList;
import java.util.List;

@Component
public class TracksRepository {

    private long currentTrack = 0;

    @PostConstruct
    public void init() {

        Tracks track = new Tracks(1, null,"3:00", "Album", "Genero", "Titulo");
        save(track);
    }

    private List<Tracks> tracks = new ArrayList<>();

    public void save(Tracks track) {
        currentTrack++;
        track.setId(currentTrack);
        tracks.add(track);
    }

    public Tracks findById(long id) {
        return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
    }

    public Tracks findByTitle(String title) {
        return tracks.stream().filter(track -> track.getTitle().equals(title)).findFirst().orElse(null);
    }

    public List<Tracks> findAll() {
        return tracks;
    }

    public void delete(long id) {
        tracks.removeIf(track -> track.getId() == id);
    }
}
