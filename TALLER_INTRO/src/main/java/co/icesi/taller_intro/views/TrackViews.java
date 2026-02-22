package co.icesi.taller_intro.views;

import java.util.List;
import java.util.stream.Collectors;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;

public class TrackViews {

    public String buildTrackForm(List<Artist> artistsList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Add Track</h2>");
        sb.append("<form action='tracks' method='post'>");
        sb.append("<input type='hidden' name='action' value='create'>");
        sb.append("ID: <input type='number' name='id' required><br>");
        sb.append("Title: <input type='text' name='title' required><br>");
        sb.append("Duration: <input type='text' name='duration' required><br>");
        sb.append("Album: <input type='text' name='album'><br>");
        sb.append("Genre: <input type='text' name='genre'><br>");

        sb.append("<h3>Select Artists</h3>");
        if (artistsList != null && !artistsList.isEmpty()) {
            for (Artist a : artistsList) {
                sb.append("<input type='checkbox' name='artistIds' value='");
                sb.append(a.getId());
                sb.append("'> ");
                sb.append(a.getName());
                sb.append("<br>");
            }
        } else {
            sb.append("No artists available to assign.");
        }

        sb.append("<br/><button type='submit'>Add Track</button>");
        sb.append("</form>");
        return sb.toString();
    }

    public String buildTrackTable(List<Tracks> tracksList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Track List</h2>");
        sb.append("<table border='1' style='border-collapse: collapse; width: 100%;'>");
        sb.append("<tr><th>ID</th><th>Title</th><th>Duration</th><th>Album</th><th>Genre</th><th>Artists</th></tr>");

        if (tracksList != null && !tracksList.isEmpty()) {
            for (Tracks t : tracksList) {
                sb.append("<tr>");
                sb.append("<td>").append(t.getId()).append("</td>");
                sb.append("<td>").append(t.getTitle()).append("</td>");
                sb.append("<td>").append(t.getDuration()).append("</td>");
                sb.append("<td>").append(t.getAlbum() != null ? t.getAlbum() : "-").append("</td>");
                sb.append("<td>").append(t.getGenre() != null ? t.getGenre() : "-").append("</td>");
                sb.append("<td>");
                if (t.getArtists() != null && !t.getArtists().isEmpty()) {
                    String artists = t.getArtists().stream()
                                      .map(Artist::getName)
                                      .collect(Collectors.joining(", "));
                    sb.append(artists);
                }
                sb.append("</td>");
                sb.append("</tr>");
            }
        } else {
            sb.append("<tr><td colspan='6'>No tracks found.</td></tr>");
        }

        sb.append("</table>");
        return sb.toString();
    }

    public String buildDeleteForm(List<Tracks> tracksList) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Delete Track</h2>");
        sb.append("<form action='tracks' method='post'>");
        sb.append("<input type='hidden' name='action' value='delete'>");

        sb.append("<select name='trackId'>");
        if (tracksList != null && !tracksList.isEmpty()) {
            for (Tracks t : tracksList) {
                sb.append("<option value='").append(t.getId()).append("'>").append(t.getTitle()).append("</option>");
            }
        }
        sb.append("</select>");

        sb.append(" <button type='submit'>Delete</button>");
        sb.append("</form>");
        return sb.toString();
    }
}