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

    private static List<Artist> artists = new ArrayList<Artist>();

    public static void save(Artist artist) {
        artists.add(artist);
    }

    public static void deleteById(long id) {
        artists.removeIf(a -> a.getId() == id);
    }

    public static Artist findById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst().orElse(null);
    }

    public static List<Artist> findAll() {
        return artists;
    }

    public static List<Artist> getAll() {
        return artists;
    }

    public static void initializeData(){
        for (int i = 1; i <= 10; i++) {
            Artist artist = new Artist(i, "Artist " + i, "Country " + i);
            artists.add(artist);
        }

        int trackIndex = 0;

        // assign 5 tracks per artist if tracks were initialized
        if (co.icesi.taller_intro.repositories.TracksRepository.getAll().size() >= 50) {
            for (Artist artist : ArtistRepository.getAll()) {
                List<Tracks> artistTracks = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    artistTracks.add(co.icesi.taller_intro.repositories.TracksRepository.getAll().get(trackIndex));
                    trackIndex++;
                }

                artist.setTracks(artistTracks);
            }
        }
    }

    public static Artist findByName(String name) {
        return artists.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }


}
