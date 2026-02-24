package org.example.parcial_a.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.parcial_a.App.AppContext;
import org.example.parcial_a.model.Device;
import org.example.parcial_a.services.IoTServices;

import java.io.IOException;

@WebServlet("/device")
public class DeviceServlet extends HttpServlet {

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
        out.println("<h1>Device</h1>");
        out.println("<form action='device' method='post'>");
        out.println("<input type='text' name='id'/><br/>");
        out.println("<input type='text' name='name'/><br/>");
        out.println("<input type='text' name='serialNumber'/><br/>");
        out.println("<input type='text' name='location'/><br/>");
        out.println("<input type='text' name='type'/><br/>");
        out.println("<input type='text' name='status'/><br/>");
        out.println("<button type='submit'>Add Device</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String serialNumber = req.getParameter("serialNumber");
        String location = req.getParameter("location");
        String type = req.getParameter("type");
        String status = req.getParameter("status");

        iot.addDevice(new Device(id, status, type, location, serialNumber, name));

        resp.sendRedirect("/parcial/device");

    }
}
