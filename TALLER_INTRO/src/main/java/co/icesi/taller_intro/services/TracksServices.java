package co.icesi.taller_intro.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.TracksRepository;

@Service
public class TracksServices {
    @Autowired
    TracksRepository tracksRepository;
    @Autowired
    ArtistService artistService;

    public void save(Tracks track) {
        Tracks found = tracksRepository.findAll().stream()
                .filter(t -> t.getId() == track.getId())
                .findFirst()
                .orElse(null);
        if (found != null) {
            System.out.println("La pista ya existe");
            
        }else{
            tracksRepository.save(track);
        }     
        
    }


    public void delete(long id) {
        Tracks found = tracksRepository.findAll().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        if (found == null) {
            System.out.println("La pista no existe");
        } else {
        tracksRepository.deleteById(id);
    }
    }

    public ArrayList<Tracks> findAll() {
        return tracksRepository.findAll();
    }


}
