package co.icesi.taller_intro.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.services.ArtistService;
import co.icesi.taller_intro.views.ArtistViews;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {

private ArtistViews artistViews;
private ArtistService artistService;

    @Override
    public void init() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        artistService = context.getBean(ArtistService.class);
        artistViews = new ArtistViews();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<link rel='stylesheet' href='css/style.css'>");
        sb.append("</head>");
        sb.append("<body>");

        if ("search".equals(action)) {
            String name = req.getParameter("name");
            Artist artist = artistService.getArtistByNameWithTracks(name);

            sb.append("<h2>Resultado de búsqueda</h2>");
            sb.append(artistViews.showArtistDetails(artist));
        }
        List<Artist> artists = artistService.getAll();
        sb.append("<h1>Lista de Artistas</h1>");
        sb.append(artistViews.listArtists(artists));

        sb.append("<h2>Crear Artista</h2>");
        sb.append(artistViews.createArtistForm());

        sb.append("<h2>Buscar Artista</h2>");
        sb.append(artistViews.searchArtistForm());

        sb.append("<h2>Eliminar Artista</h2>");
        sb.append(artistViews.deleteArtistForm());

        sb.append("</body></html>");

        resp.setContentType("text/html");
        resp.getWriter().println(sb.toString());
      
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String nationality = req.getParameter("nationality");

            Artist artist = new Artist(id, new HashSet<>(), nationality, name);
            artistService.save(artist);
        }

        if ("delete".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            artistService.deleteArtistById(id);
        }

        resp.sendRedirect("artists");
    }

}