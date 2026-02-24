package co.icesi.taller_intro.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.repositories.ArtistRepository;

@Service
public class ArtistService {
    
    @Autowired
    ArtistRepository artistRepository;

    public void save(Artist artist) {
        Artist found = artistRepository.getAll().stream()
                .filter(a -> a.getId() == artist.getId())
                .findFirst()
                .orElse(null);
        if (found == null) {        
        artistRepository.save(artist);
    }else {
        System.out.println("El artista ya existe");
    }
    }

    public ArrayList<Artist> getAll() {
        return artistRepository.getAll();
    }

    public void deleteArtistById(long id) {
        Artist foundArtist = artistRepository.getAll().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
                if(foundArtist !=null){
                    artistRepository.deleteById(id);
                }else{
                    System.out.println("El artista no existe");
                }
        
    }

    public Artist getArtistByNameWithTracks(String name) {
        return artistRepository.getAll().stream()
                .filter(a -> a.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
