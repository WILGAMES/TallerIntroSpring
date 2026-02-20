package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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

@WebServlet("/track")
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Tracks> tracks = tracksServices.findAll();
		
		StringBuilder sb  =  new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel='stylesheet' href='css/style.css'>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(trackViews.listTracks(tracks));
		sb.append("</body></html>");

		resp.setContentType("text/html");
		resp.getWriter().write(sb.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action  = req.getParameter("action");

				if ("create".equals(action)) {

				long id = Long.parseLong(req.getParameter("id"));
				String title = req.getParameter("title");
				String genre = req.getParameter("genre");
				String duration = req.getParameter("duration");
				String albumTitle = req.getParameter("albumTitle");

				String[] artistIds = req.getParameterValues("artists");

				List<Artist> selectedArtists = new ArrayList<>();

				if (artistIds != null) {
					for (String artistId : artistIds) {
						Artist artist = artistService.getArtistById(Long.parseLong(artistId));
						if (artist != null) {
							selectedArtists.add(artist);
						}
					}
				}
				Tracks track = new Tracks(id, new HashSet<>(selectedArtists), duration, albumTitle, genre, title);
				tracksServices.save(track);
	}

	if ("delete".equals(action)) {
		long id = Long.parseLong(req.getParameter("id"));
		tracksServices.delete(id);
	}

	resp.sendRedirect("tracks");
}
}

