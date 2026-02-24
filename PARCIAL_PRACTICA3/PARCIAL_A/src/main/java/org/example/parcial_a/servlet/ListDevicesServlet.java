package org.example.parcial_a.servlet;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parcial_a.model.Device;
import org.example.parcial_a.services.IoTServices;
import org.example.parcial_a.App.AppContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/list")
public class ListDevicesServlet extends HttpServlet {

    IoTServices iot;

    @Override
    public void init() throws ServletException {
        iot = AppContext.getInstance().getBean(IoTServices.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        var out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>Devices</h1>");
        for(Device device : iot.getDevices()){
            out.println("<p>" +device.getName()+"</p>");
            out.println("<p>" +device.getId()+"</p>");
            out.println("<p>" +device.getSerialName()+"</p>");
            out.println("<p>" +device.getLocation()+"</p>");
            out.println("<p>" +device.getType()+"</p>");
            out.println("<p>" +device.getStatus()+"</p>");
        }

        out.println("</body></html>");
    }

}
