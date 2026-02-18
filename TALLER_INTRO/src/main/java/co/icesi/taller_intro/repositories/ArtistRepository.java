package co.icesi.taller_intro.repositories;


import co.icesi.taller_intro.model.Artist;
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

    private List<Artist> artists = new ArrayList<Artist>();

    public void save(Artist artist) {
        currentArtist++;
        artist.setId(currentArtist);
        artists.add(artist);
    }

    public Artist findById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst().orElse(null);
    }

    public List<Artist> findAll() {
        return artists;
    }

}
