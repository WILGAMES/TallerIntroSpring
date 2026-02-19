package co.icesi.taller_intro.views;

import java.util.Collection;
import java.util.List;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;

public class TrackViews {

    public static String listTracks(Collection<Tracks> tracks) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><meta charset=\"utf-8\">\n");
        sb.append("<link rel=\"stylesheet\" href=\"css/style.css\">\n");
        sb.append("<title>Tracks</title></head><body><div class=\"container\">\n");
        sb.append("<h1>Tracks</h1>\n");

        sb.append("<div class=\"card\">\n");
        // create form with artist checkboxes
        sb.append(createTrackForm(null));
        sb.append("</div>\n");

        sb.append("<div class=\"card\">\n");
        sb.append("<table>");
        sb.append("<tr><th>ID</th><th>Title</th><th>Duration</th><th>Artists</th><th>Actions</th></tr>");
        for (Tracks t : tracks) {
            sb.append("<tr>");
            sb.append("<td>").append(t.getId()).append("</td>");
            sb.append("<td>").append(t.getTitle()).append("</td>");
            sb.append("<td>").append(t.getDuration()).append("</td>");
            sb.append("<td><ul>");
            if (t.getArtists() != null) {
                t.getArtists().forEach(a -> sb.append("<li>").append(a.getName()).append("</li>"));
            }
            sb.append("</ul></td>");
            sb.append("<td><a class=\"delete\" href=\"track?action=delete&id=").append(t.getId()).append("\">Delete</a></td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</div>");

        sb.append("</div></body></html>");
        return sb.toString();
    }

    public static String createTrackForm(List<Artist> allArtists) {
        StringBuilder sb = new StringBuilder();
        sb.append("<form method=\"post\" action=\"track\" class=\"inline\">\n");
        sb.append("<input type=\"hidden\" name=\"action\" value=\"create\" />\n");
        sb.append("Title: <input type=\"text\" name=\"title\" />\n");
        sb.append("Duration: <input type=\"text\" name=\"duration\" />\n");
        sb.append("<div><strong>Select Artists</strong><br/>");
        if (allArtists != null) {
            for (Artist a : allArtists) {
                sb.append("<label><input type=\"checkbox\" name=\"artists\" value=\"").append(a.getId()).append("\" /> ").append(a.getName()).append("</label><br/>");
            }
        } else {
            sb.append("(artists loaded on page)");
        }
        sb.append("</div>");
        sb.append("<button type=\"submit\">Create Track</button>\n");
        sb.append("</form>\n");
        return sb.toString();
    }
}
