package co.icesi.taller_intro.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Tracks;
import jakarta.annotation.PostConstruct;

@Component
public class TracksRepository {

    private long currentTrack = 0;

    @PostConstruct
    public void init() {

        Tracks track = new Tracks(1, null,"3:00", "Album", "Genero", "Titulo");
        save(track);
    }

    private static List<Tracks> tracks = new ArrayList<>();

    public static void save(Tracks track) {
        tracks.add(track);
    }

    public static Tracks findById(long id) {
        return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
    }

    public static Tracks findByTitle(String title) {
        return tracks.stream().filter(track -> track.getTitle().equals(title)).findFirst().orElse(null);
    }

    public static List<Tracks> findAll() {
        return tracks;
    }

    public static List<Tracks> getAll() {
        return tracks;
    }

    public static void deleteById(long id) {
        tracks.removeIf(track -> track.getId() == id);
    }

    public static void initializeTracks() {
        for (int i = 1; i <= 50; i++) {
            Tracks track = new Tracks(i, "Track " + i, "180" + i);
            tracks.add(track);
        }
    }
}
