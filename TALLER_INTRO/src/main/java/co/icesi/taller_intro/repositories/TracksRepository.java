package co.icesi.taller_intro.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.icesi.taller_intro.model.Tracks;

@Repository
public class TracksRepository {

    ArrayList<Tracks> tracks = new ArrayList<>();

    public void save(Tracks track) {
        tracks.add(track);
    }

    public Tracks findById(long id) {
        return tracks.stream().filter(track -> track.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Tracks> findAll() {
        return tracks;
    }

    public void deleteById(long id) {
        tracks.removeIf(track -> track.getId() == id);
    }

}
