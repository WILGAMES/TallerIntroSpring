package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.services.ArtistService;
import co.icesi.taller_intro.services.TracksServices;
import co.icesi.taller_intro.views.TrackViews;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {

	private TrackViews trackViews;
	private TracksServices tracksServices;
	private ArtistService artistService;

	@Override
	public void init()  {
		ApplicationContext context  =  WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		tracksServices = context.getBean(TracksServices.class);
		trackViews = new TrackViews();
		artistService = context.getBean(ArtistService.class);
	}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    List<Artist> artistsList = artistService.getAll();
    List<Tracks> tracksList = tracksServices.findAll();

    StringBuilder sb = new StringBuilder();

    sb.append("<html>");
    sb.append("<head>");
    sb.append("<link rel='stylesheet' href='css/style.css'>");
    sb.append("</head>");
    sb.append("<body>");

    sb.append("<h1>Tracks</h1>");

    sb.append("<h2>Add Track</h2>");
    sb.append("<form action='tracks' method='post'>");

    sb.append("Title: <input type='text' name='title' required><br>");
    sb.append("Duration: <input type='text' name='duration' required><br>");
    sb.append("Album: <input type='text' name='album'><br>");
    sb.append("Genre: <input type='text' name='genre'><br>");

    sb.append("<h3>Select Artists</h3>");

    for (Artist a : artistsList) {
        sb.append("<input type='checkbox' name='artistIds' value='");
        sb.append(a.getId());
        sb.append("'> ");
        sb.append(a.getName());
        sb.append("<br>");
    }

    sb.append("<button type='submit'>Add Track</button>");
    sb.append("</form>");

    sb.append("<table>");
    sb.append("<tr><th>ID</th><th>Title</th><th>Duration</th><th>Album</th><th>Genre</th><th>Artists</th></tr>");

    for (Tracks t : tracksList) {
        sb.append("<tr>");
        sb.append("<td>").append(t.getId()).append("</td>");
        sb.append("<td>").append(t.getTitle()).append("</td>");
        sb.append("<td>").append(t.getDuration()).append("</td>");
        sb.append("<td>").append(t.getAlbum() != null ? t.getAlbum() : "-").append("</td>");
        sb.append("<td>").append(t.getGenre() != null ? t.getGenre() : "-").append("</td>");
        sb.append("<td>");
        if (t.getArtists() != null && !t.getArtists().isEmpty()) {
            for (Artist a : t.getArtists()) {
                sb.append(a.getName()).append(", ");
            }
        }
        sb.append("</td>");
        sb.append("</tr>");
    }

    sb.append("</table>");

    sb.append("<h2>Delete Track</h2>");
    sb.append("<form action='tracks' method='post'>");
    sb.append("<input type='hidden' name='action' value='delete'>");

    sb.append("<select name='trackId'>");

    for (Tracks t : tracksList) {
        sb.append("<option value='");
        sb.append(t.getId());
        sb.append("'>");
        sb.append(t.getTitle());
        sb.append("</option>");
    }

    sb.append("</select>");

    sb.append("<button type='submit'>Delete</button>");
    sb.append("</form>");

    sb.append("</body></html>");

    response.getWriter().write(sb.toString());
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("delete".equals(action)) {
			long trackId = Long.parseLong(req.getParameter("trackId"));
			tracksServices.delete(trackId);
		} else {
			// CREAR TRACK
			String title = req.getParameter("title");
			String duration = req.getParameter("duration");
			String album = req.getParameter("album");
			String genre = req.getParameter("genre");

			List<Artist> artistsList = artistService.getAll();
			Tracks newTrack = new Tracks(tracksServices.findAll().size() + 1, title, duration);
			newTrack.setAlbum(album);
			newTrack.setGenre(genre);

			String[] artistIds = req.getParameterValues("artistIds");

			if (artistIds != null) {
				for (String idStr : artistIds) {
					long artistId = Long.parseLong(idStr);

					for (Artist a : artistsList) {
						if (a.getId() == artistId) {
							newTrack.addArtist(a);
						}
					}
				}
			}

			tracksServices.save(newTrack);
		}

		resp.sendRedirect("tracks");
	}
}

