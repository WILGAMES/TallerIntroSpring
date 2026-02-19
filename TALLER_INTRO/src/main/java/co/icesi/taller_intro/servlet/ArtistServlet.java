package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.repositories.ArtistRepository;
import co.icesi.taller_intro.views.ArtistViews;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

    private Gson gson = new Gson();
    private ArtistViews artistViews;

    @Override
    public void init() {
        // Initialization of data is performed in ServletContextListener (Intializer)
        // Use static repositories directly; no Spring ApplicationContext required.
        artistViews = new ArtistViews();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        response.setContentType("text/html; charset=UTF-8");

        if (action == null || "list".equals(action)) {
            List<Artist> artists = ArtistRepository.getAll();
            response.getWriter().write(ArtistViews.listArtists(artists));
            return;
        }

        if ("search".equals(action)) {
            String name = request.getParameter("name");
            Artist artist = ArtistRepository.findByName(name);
            response.getWriter().write(ArtistViews.artistDetail(artist));
            return;
        }

        if ("delete".equals(action)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                long id = Long.parseLong(idParam);
                ArtistRepository.deleteById(id);
            }
            response.sendRedirect("artist?action=list");
            return;
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            String name = req.getParameter("name");
            String country = req.getParameter("country");

            long generatedId = ArtistRepository.getAll().stream().mapToLong(Artist::getId).max().orElse(0L) + 1;
            Artist artist = new Artist(generatedId, name, country);
            ArtistRepository.save(artist);

            resp.sendRedirect("artist?action=list");
            return;
        }

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void destroy () {
        // No application context to close when not using Spring here
    }

}
