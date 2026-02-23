package org.example.parcial_practica2.servlet;

import org.example.parcial_practica2.model.Flight;
import org.example.parcial_practica2.services.FlightServices;
import org.example.parcial_practica2.App.AppContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/delete-flight")
public class DeleteFlightServlet extends HttpServlet {

    FlightServices flightServices;

    public void init() throws ServletException {
        flightServices = AppContext.getInstance().getBean("flightServices", FlightServices.class);
    }


    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws ServletException, java.io.IOException {
        String flightId = req.getParameter("flightId");
        flightServices.removeFlight(flightId);
        resp.sendRedirect("./");
    }

}
