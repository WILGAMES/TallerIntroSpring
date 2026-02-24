package org.example.parcial_a.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parcial_a.App.AppContext;
import org.example.parcial_a.services.IoTServices;

import java.io.IOException;

@WebServlet("/update")
public class UpdateStateServlet extends HttpServlet {

    IoTServices iot;
    @Override
    public void init() throws ServletException {
        super.init();
        iot = AppContext.getInstance().getBean(IoTServices.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        var out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Update Device State</h1>");
        out.println("<form action='update' method='post'>");
        out.println("<input type='text' name='id'/><br/>");
        out.println("<input type='text' name='state'/><br/>");
        out.println("<button type='submit'>Update State</button>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String state = req.getParameter("state");
        Integer id = Integer.parseInt(req.getParameter("id"));
        
        iot.updateState(id, state);
        resp.sendRedirect("/parcial/update");

    }
}

