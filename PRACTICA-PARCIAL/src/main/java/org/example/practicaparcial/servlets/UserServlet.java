package org.example.practicaparcial.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.practicaparcial.model.User;
import org.example.practicaparcial.services.UserService;
import org.example.practicaparcial.vistas.UsersViews;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.google.gson.Gson;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService;
    private Gson gson = new Gson();
    private ApplicationContext context;
    private UsersViews usersViews;

    @Override
    public void init(){
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userService = context.getBean(UserService.class);
        usersViews = new UsersViews();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    List<User> users = userService.getUsers();
    StringBuilder sb = new StringBuilder();
    sb.append("<html>");
    sb.append("<body>");
    sb.append(usersViews.listUsers(users));
    sb.append("</body>");
    sb.append("</html>");

    resp.setContentType("text/html");
    resp.getWriter().println(sb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        BufferedReader reader = req.getReader();

        String body = "";
        boolean end = false;
        while(!end){
            String current = reader.readLine();
            if(current == null){
                end = true;
            }else{
                body += current;
            }
        }
        @SuppressWarnings({"unchecked", "unused"})
        Map<String,String> data = gson.fromJson(body, HashMap.class);
        // Datos parseados correctamente
    }

    @Override
    public void destroy(){
        if(context != null){
            ((ClassPathXmlApplicationContext)context).close();
        }
    }

}
