package co.icesi.taller_intro.config;

import java.util.ArrayList;
import java.util.List;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.ArtistRepository;
import co.icesi.taller_intro.repositories.TracksRepository;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Intializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // clear existing data to avoid duplicates
        TracksRepository.getAll().clear();
        ArtistRepository.getAll().clear();

        // Crear 50 tracks
        for (int i = 1; i <= 50; i++) {
            Tracks track = new Tracks(i, "Track " + i, String.valueOf(180 + i));
            TracksRepository.save(track);
        }

        // Crear 10 artistas
        for (int i = 1; i <= 10; i++) {
            Artist artist = new Artist(i, "Artist " + i, "Country " + i);
            ArtistRepository.save(artist);
        }

        // Asignar 5 tracks por artista
        int trackIndex = 0;
        List<Tracks> allTracks = TracksRepository.getAll();

        for (Artist artist : ArtistRepository.getAll()) {
            List<Tracks> artistTracks = new ArrayList<>();

            for (int j = 0; j < 5; j++) {
                if (trackIndex < allTracks.size()) {
                    Tracks t = allTracks.get(trackIndex);
                    artistTracks.add(t);
                    // maintain bidirectional link
                    t.getArtists().add(artist);
                    trackIndex++;
                }
            }

            artist.setTracks(artistTracks);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do
    }
}
