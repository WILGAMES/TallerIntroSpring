package org.example.parcial_practica2.servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parcial_practica2.App.AppContext;
import org.example.parcial_practica2.model.Flight;
import org.example.parcial_practica2.services.FlightServices;

import java.io.IOException;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {

    FlightServices flightServices;

    @Override
    public void init(ServletConfig config) throws ServletException {
        flightServices = AppContext.getInstance().getBean("flightServices", FlightServices.class);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Adquirir parametros

        String id = req.getParameter("id");
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        String date = req.getParameter("date");
        System.out.println("id: " + id);
        System.out.println("origin: " + origin);
        System.out.println("destination: " + destination);
        System.out.println("date: " + date);

        flightServices.addFlight(new Flight(id, origin, destination, date));

        resp.sendRedirect("./");

    }
}
