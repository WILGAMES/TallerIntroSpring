package co.icesi.taller_intro.views;

import java.util.List;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;

public class TrackViews {

    // LISTAR TRACKS
    public String listTracks(List<Tracks> tracks) {

        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<theader>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>Title</th>");
        sb.append("<th>Genre</th>");
        sb.append("<th>Duration</th>");
        sb.append("<th>Album</th>");
        sb.append("<th>Artists</th>");
        sb.append("</tr>");
        sb.append("</theader>");

        sb.append("<tbody>");
        for (Tracks track : tracks) {

            sb.append("<tr>");
            sb.append("<td>" + track.getId() + "</td>");
            sb.append("<td>" + track.getTitle() + "</td>");
            sb.append("<td>" + track.getGenre() + "</td>");
            sb.append("<td>" + track.getDuration() + "</td>");
            sb.append("<td>" + track.getAlbum() + "</td>");

            sb.append("<td>");
            for (Artist artist : track.getArtists()) {
                sb.append(artist.getName() + "<br>");
            }
            sb.append("</td>");

            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");

        return sb.toString();
    }

    // FORMULARIO CREAR TRACK
    public String createTrackForm(List<Artist> artists) {

        StringBuilder sb = new StringBuilder();

        sb.append("<form method='post' action='tracks'>");
        sb.append("<input type='hidden' name='action' value='create'/>");

        sb.append("ID: <input type='number' name='id'/><br>");
        sb.append("Title: <input type='text' name='title'/><br>");
        sb.append("Genre: <input type='text' name='genre'/><br>");
        sb.append("Duration: <input type='text' name='duration'/><br>");
        sb.append("Album: <input type='text' name='albumTitle'/><br>");

        sb.append("<p>Select Artists:</p>");

        for (Artist artist : artists) {
            sb.append("<input type='checkbox' name='artists' value='" + artist.getId() + "'/>");
            sb.append(artist.getName() + "<br>");
        }

        sb.append("<button type='submit'>Create Track</button>");
        sb.append("</form>");

        return sb.toString();
    }

    // FORMULARIO ELIMINAR TRACK
    public String deleteTrackForm() {

        StringBuilder sb = new StringBuilder();

        sb.append("<form method='post' action='tracks'>");
        sb.append("<input type='hidden' name='action' value='delete'/>");

        sb.append("ID: <input type='number' name='id'/>");
        sb.append("<button type='submit'>Delete</button>");
        sb.append("</form>");

        return sb.toString();
    }
}