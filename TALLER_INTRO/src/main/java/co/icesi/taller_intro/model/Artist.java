package co.icesi.taller_intro.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Artist {

private long id;
private String name;
private String nationality;

private Set<Tracks> tracks = new HashSet<>();

    public Artist(long id, HashSet<Tracks> tracks, String nationality, String name) {
        this.id = id;
        this.tracks = tracks;
        this.nationality = nationality;
        this.name = name;
    }

    public long getId() {
    return id;
}
public void setId(long id) {
    this.id = id;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}

public String getNationality() {
    return nationality;
}

public void setNationality(String nationality) {
    this.nationality = nationality;
}

    public Set<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Tracks> tracks) {
        this.tracks = tracks;
    }

    //M:N bidireccional
    public void addTracks(Tracks tracks) {
        this.tracks.add(tracks);
        tracks.getArtists().add(this);
    }
}
