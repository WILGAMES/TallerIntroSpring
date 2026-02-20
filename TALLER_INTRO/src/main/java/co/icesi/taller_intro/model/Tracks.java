package co.icesi.taller_intro.model;

import java.util.HashSet;
import java.util.Set;

public class Tracks {

    private long id;
    private String title;
    private String genre;
    private String album;
    private String duration;

    private Set<Artist> artists = new HashSet<>();

    public Tracks(long id, Set<Artist> artists, String duration, String album, String genre, String title) {
        this.id = id;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
        this.title = title;
    }

    public Tracks(long id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album = null;
        this.genre = null;
        this.artists = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getTracks().add(this);
    }
}
