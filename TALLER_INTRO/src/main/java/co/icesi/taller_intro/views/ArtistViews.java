package co.icesi.taller_intro.views;

import java.util.List;

import co.icesi.taller_intro.model.Artist;

public class ArtistViews {

    public static String listArtists(List<Artist> artists) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><meta charset=\"utf-8\">\\n");
        sb.append("<link rel=\"stylesheet\" href=\"css/style.css\">\\n");
        sb.append("<title>Artists</title></head><body><div class=\"container\">\\n");
        sb.append("<h1>Artists</h1>\\n");

        sb.append("<div class=\"card\">\\n");
        // create form
        sb.append(createArtistForm());
        sb.append("</div>\\n");

        sb.append("<div class=\"card\">\\n");
        sb.append("<h2 class=\"small muted\">List of artists</h2>\\n");
        sb.append("<table>");
        sb.append("<tr><th>ID</th><th>Name</th><th>Country</th><th>Tracks</th><th>Actions</th></tr>");
        for (Artist a : artists) {
            sb.append("<tr>");
            sb.append("<td>").append(a.getId()).append("</td>");
            sb.append("<td><a href=\"artist?action=search&name=").append(a.getName()).append("\">").append(a.getName()).append("</a></td>");
            sb.append("<td>").append(a.getNationality()).append("</td>");
            sb.append("<td><ul>");
            if (a.getTracks() != null) {
                a.getTracks().forEach(t -> sb.append("<li>").append(t.getTitle()).append("</li>"));
            }
            sb.append("</ul></td>");
            sb.append("<td><a class=\"delete\" href=\"artist?action=delete&id=").append(a.getId()).append("\">Delete</a></td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</div>");

        sb.append("</div></body></html>");
        return sb.toString();
    }

    public static String createArtistForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method=\"post\" action=\"artist\" class=\"inline\">\\n");
        sb.append("<input type=\"hidden\" name=\"action\" value=\"create\" />\\n");
        sb.append("Name: <input type=\"text\" name=\"name\" />\\n");
        sb.append("Country: <input type=\"text\" name=\"country\" />\\n");
        sb.append("<button type=\"submit\">Create</button>\\n");
        sb.append("</form>\\n");
        return sb.toString();
    }

    public static String searchArtistForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method=\"get\" action=\"artist\" class=\"inline\">\\n");
        sb.append("<input type=\"hidden\" name=\"action\" value=\"search\" />\\n");
        sb.append("<input type=\"text\" name=\"name\" placeholder=\"Artist name\" />\\n");
        sb.append("<button type=\"submit\">Search</button>\\n");
        sb.append("</form>\\n");
        return sb.toString();
    }

    public static String artistDetail(Artist artist) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><meta charset=\"utf-8\">\\n");
        sb.append("<link rel=\"stylesheet\" href=\"../css/style.css\">\\n");
        sb.append("<title>Artist Detail</title></head><body><div class=\"container\">\\n");
        if (artist == null) {
            sb.append("<p>No artist found.</p>");
        } else {
            sb.append("<h1>").append(artist.getName()).append("</h1>");
            sb.append("<p><strong>ID:</strong> ").append(artist.getId()).append("</p>");
            sb.append("<p><strong>Country:</strong> ").append(artist.getNationality()).append("</p>");
            sb.append("<h3>Tracks</h3>");
            sb.append("<ul>");
            if (artist.getTracks() != null) {
                artist.getTracks().forEach(t -> sb.append("<li>").append(t.getTitle()).append(" (" + t.getDuration() + ")</li>"));
            }
            sb.append("</ul>");
        }
        sb.append("<p><a href=\"artist?action=list\">Back to artists</a></p>");
        sb.append("</div></body></html>");
        return sb.toString();
    }
}
