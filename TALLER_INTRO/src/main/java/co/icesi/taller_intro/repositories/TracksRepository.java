package co.icesi.taller_intro.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Tracks;
import jakarta.annotation.PostConstruct;

@Component
public class TracksRepository {

    @PostConstruct
    public void init() {

        Tracks track = new Tracks(0, null,"3:00", "Album", "Genero", "Titulo");
        save(track);
    }

    private List<Tracks> tracks = new ArrayList<>();

    public void save(Tracks track) {
        tracks.add(track);
    }

    public Tracks findById(long id) {
        return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
    }

    public List<Tracks> findAll() {
        return tracks;
    }

    public void deleteById(long id) {
        tracks.removeIf(track -> track.getId() == id);
    }

}
