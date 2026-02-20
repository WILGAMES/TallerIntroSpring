package co.icesi.taller_intro.repositories;


import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistRepository {

    private long currentArtist = 0;

    @PostConstruct
    public void init() {
        Artist artist = new Artist(0, null, "Nacionalidad", "Artista");
        save(artist);
    }

    private  List<Artist> artists = new ArrayList<Artist>();

    public void save(Artist artist) {
        if (artist.getId() == 0) {
            currentArtist++;
            artist.setId(currentArtist);
        }
        artists.add(artist);
    }

    public List<Artist> getAll() {
        return artists;
    }

    public Artist findByName(String name) {
        return artists.stream()
                .filter(a -> a.getName() != null && a.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(long id) {
        artists.removeIf(a -> a.getId() == id);
    }

    public Artist findById(long id) {
        return artists.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


}
