package org.example.parcial_practica2.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parcial_practica2.App.AppContext;
import org.example.parcial_practica2.model.Passanger;
import org.example.parcial_practica2.services.FlightServices;

import java.io.IOException;

@WebServlet("/passanger")
public class PassangerServlet extends HttpServlet {

    private FlightServices flightServices;

    @Override
    public void init() throws ServletException {

        flightServices = AppContext.getInstance().getBean("flightServices",FlightServices.class);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id =req.getParameter("id");
        String name = req.getParameter("name");
        String passportId = req.getParameter("passportId");
        String flightId = req.getParameter("flightId");

        flightServices.addPassanger(new Passanger(id, name, passportId, flightId));

        resp.sendRedirect("./");
    }
}
