package co.icesi.taller_intro.views;

import java.util.List;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;

public class ArtistViews {

    // LISTAR ARTISTAS
    public String listArtists(List<Artist> artists) {

        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<theader>");
        sb.append("<tr>");
        sb.append("<th>ID</th>");
        sb.append("<th>Name</th>");
        sb.append("<th>Nationality</th>");
        sb.append("</tr>");
        sb.append("</theader>");

        sb.append("<tbody>");
        for (Artist artist : artists) {
            sb.append("<tr>");
            sb.append("<td>" + artist.getId() + "</td>");
            sb.append("<td>" + artist.getName() + "</td>");
            sb.append("<td>" + artist.getNationality() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");

        return sb.toString();
    }

    // FORMULARIO CREAR
    public String createArtistForm() {

        StringBuilder sb = new StringBuilder();

        sb.append("<form method='post' action='artists'>");
        sb.append("<input type='hidden' name='action' value='create'/>");

        sb.append("ID: <input type='number' name='id'/><br>");
        sb.append("Name: <input type='text' name='name'/><br>");
        sb.append("Nationality: <input type='text' name='nationality'/><br>");

        sb.append("<button type='submit'>Create</button>");
        sb.append("</form>");

        return sb.toString();
    }

    // FORMULARIO BUSCAR
    public String searchArtistForm() {

        StringBuilder sb = new StringBuilder();

        sb.append("<form method='get' action='artists'>");
        sb.append("<input type='hidden' name='action' value='search'/>");

        sb.append("Name: <input type='text' name='name'/>");
        sb.append("<button type='submit'>Search</button>");
        sb.append("</form>");

        return sb.toString();
    }

    // FORMULARIO ELIMINAR
    public String deleteArtistForm() {

        StringBuilder sb = new StringBuilder();

        sb.append("<form method='post' action='artists'>");
        sb.append("<input type='hidden' name='action' value='delete'/>");

        sb.append("ID: <input type='number' name='id'/>");
        sb.append("<button type='submit'>Delete</button>");
        sb.append("</form>");

        return sb.toString();
    }

    // MOSTRAR ARTISTA CON CANCIONES
    public String showArtistDetails(Artist artist) {

        if (artist == null) {
            return "<p>Artist not found</p>";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<h3>Artist Details</h3>");
        sb.append("<p>ID: " + artist.getId() + "</p>");
        sb.append("<p>Name: " + artist.getName() + "</p>");
        sb.append("<p>Nationality: " + artist.getNationality() + "</p>");

        sb.append("<h4>Tracks:</h4>");
        sb.append("<ul>");

        for (Tracks track : artist.getTracks()) {
            sb.append("<li>" + track.getTitle() + "</li>");
        }

        sb.append("</ul>");

        return sb.toString();
    }
}