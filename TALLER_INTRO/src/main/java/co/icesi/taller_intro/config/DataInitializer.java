package co.icesi.taller_intro.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.services.ArtistService;
import co.icesi.taller_intro.services.TracksServices;
import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ArtistService artistService;
    private final TracksServices tracksServices;

    public DataInitializer(ArtistService artistService,
                           TracksServices tracksServices) {
        this.artistService = artistService;
        this.tracksServices = tracksServices;
    }

    @PostConstruct
    public void init() {

        List<Artist> artists = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Artist artist = new Artist(
                    i,
                    new HashSet<>(),
                    "Country " + i,
                    "Artist " + i
            );
            artistService.save(artist);
            artists.add(artist);
        }

        List<Tracks> tracks = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            Tracks track = new Tracks(
                    i,
                    new HashSet<>(),
                    "3:0" + (i % 10),
                    "Album " + (i % 5),
                    "Genre " + (i % 5),
                    "Track " + i
            );

            tracksServices.save(track);
            tracks.add(track);
        }

        int trackIndex = 0;

        for (Artist artist : artists) {
            for (int j = 0; j < 5; j++) {
                Tracks track = tracks.get(trackIndex);
                track.addArtist(artist); // relación bidireccional
                trackIndex++;
            }
        }
    }
}