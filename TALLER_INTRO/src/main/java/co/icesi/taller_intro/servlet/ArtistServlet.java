package co.icesi.taller_intro.servlet;

import co.icesi.taller_intro.model.Artist;
import co.icesi.taller_intro.services.ArtistService;
import co.icesi.taller_intro.views.ArtistViews;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

private ArtistService artistService;
private Gson gson = new Gson();
private ApplicationContext applicationContext;
private ArtistViews artistViews;

@Override
    public void init() {
    applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    artistService = (ArtistService) applicationContext.getBean(ArtistService.class);
    artistViews = new ArtistViews();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artists = artistService.getAll();
        StringBuilder sb = new StringBuilder();
sb.append("<html>");
sb.append("<body>");
sb.append(artistViews.listArtist(artists));
sb.append("</body>");
sb.append("</html>");

resp.setContentType("text/html");
resp.getWriter().println(sb.toString());

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
BufferedReader br = req.getReader();
String body = "";

boolean end = false;
while (!end) {
    String currentLine = br.readLine();
    if (currentLine == null) {
        end = true;
    }else{
        body += currentLine;
    }

    Map<String, String>data = gson.fromJson(body, HashMap.class);
}
/*
@Override
    public void destroy(){
            if(applicationContext != null){
                ((ClassPathXmlApplicationContext)applicationContext).close();
            }
        }

 */

    }




}
