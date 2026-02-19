package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.model.Tracks;
import co.icesi.taller_intro.repositories.ArtistRepository;
import co.icesi.taller_intro.repositories.TracksRepository;
import co.icesi.taller_intro.views.TrackViews;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/track")
public class TrackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action == null || "list".equals(action)) {
			List<Tracks> tracks = TracksRepository.getAll();
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(TrackViews.listTracks(tracks));
			return;
		}

		if ("delete".equals(action)) {
			String idParam = req.getParameter("id");
			if (idParam != null) {
				long id = Long.parseLong(idParam);
				TracksRepository.deleteById(id);
			}
			resp.sendRedirect("track?action=list");
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("create".equals(action)) {
			String title = req.getParameter("title");
			String duration = req.getParameter("duration");

			long generatedId = TracksRepository.getAll().stream().mapToLong(Tracks::getId).max().orElse(0L) + 1;
			Tracks track = new Tracks(generatedId, title, duration);

			String[] artistIds = req.getParameterValues("artists");
			if (artistIds != null) {
				Set<Artist> selected = new HashSet<>();
				for (String aid : artistIds) {
					try {
						long aidL = Long.parseLong(aid);
						Artist artist = ArtistRepository.findById(aidL);
						if (artist != null) {
							selected.add(artist);
							// maintain bidirectional relation
							artist.addTracks(track);
						}
					} catch (NumberFormatException e) {
						// skip
					}
				}
				track.setArtists(selected);
			}

			TracksRepository.save(track);
			resp.sendRedirect("track?action=list");
			return;
		}

		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}
}
