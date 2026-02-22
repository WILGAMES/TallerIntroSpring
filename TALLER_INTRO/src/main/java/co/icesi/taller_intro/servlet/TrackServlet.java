package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    sb.append("</head>");
    sb.append("<body>");

    sb.append("<h1>Tracks</h1>");
    sb.append(trackViews.buildTrackForm(artistsList));
    sb.append(trackViews.buildTrackTable(tracksList));
    sb.append(trackViews.buildDeleteForm(tracksList));

    sb.append("</body></html>");

    response.setContentType("text/html");
    response.getWriter().write(sb.toString());
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("delete".equals(action)) {
			long trackId = Long.parseLong(req.getParameter("trackId"));
			tracksServices.delete(trackId);
		} else if ("create".equals(action)) {
			// CREAR TRACK
			long id = Long.parseLong(req.getParameter("id"));
			String title = req.getParameter("title");
			String duration = req.getParameter("duration");
			String album = req.getParameter("album");
			String genre = req.getParameter("genre");

			Tracks newTrack = new Tracks(id, title, duration);
			newTrack.setAlbum(album);
			newTrack.setGenre(genre);

			String[] artistIds = req.getParameterValues("artistIds");

			if (artistIds != null) {
				List<Artist> allArtists = artistService.getAll();
				Map<Long, Artist> artistMap = allArtists.stream()
						.collect(Collectors.toMap(Artist::getId, Function.identity()));

				for (String idStr : artistIds) {
					long artistId = Long.parseLong(idStr);
					Artist artist = artistMap.get(artistId);
					if (artist != null) {
						newTrack.addArtist(artist);
					}
				}
			}

			tracksServices.save(newTrack);
		}

		resp.sendRedirect("tracks");
	}
}
