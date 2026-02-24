package co.icesi.taller_intro.repositories;


import java.util.ArrayList;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Artist;

@Component
public class ArtistRepository {

    ArrayList<Artist> artists = new ArrayList<>();

    public void save(Artist artist) {
        artists.add(artist);
    }

    public ArrayList<Artist> getAll() {
        return artists;
    }

    public void deleteById(long id) {
        artists.remove(id);
    }

}
