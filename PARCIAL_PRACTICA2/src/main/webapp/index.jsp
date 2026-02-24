<%@ page import="org.example.parcial_practica2.App.AppContext" %>
<%@ page import="org.example.parcial_practica2.services.FlightServices" %>
<%@ page import="org.example.parcial_practica2.model.Flight" %>
<%@ page import="org.example.parcial_practica2.model.Passanger" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parcial_Paractica</title>
</head>
<body>
<h2>Agregar Vuelo</h2>
<form action ="flight" method = "post">
  <input type="text" name="id">
    <input type="text" name="origin">
    <input type="text" name="destination">
    <input type="text" name="date">
    <button>Crear</button>
</form>

<h2>Agregar Pasajero</h2>
<form action ="passanger" method = "post">
  <input type="text" name="id">
  <input type="text" name="name">
  <input type="text" name="passportId">
  <input type="text" name="flightId">
  <button>Crear</button>
</form>

<h2>Eliminar Vuelo</h2>
<form action ="delete-flight" method = "post">
  <input type="text" name="flightId">
  <button>Eliminar</button>
</form>

<h2>Actualizar Pasajero</h2>
<form action ="update-passanger" method = "post">
  <input type="text" name="id">
  <input type="text" name="name">
  <input type="text" name="passportId">
  <input type="text" name="flightId">
  <button>Actualizar</button>
</form>

<%
  FlightServices services = AppContext.getInstance().getBean("flightServices" , FlightServices.class);
  for(Flight flight : services.getFlights()) {
    out.println("<h1>"+flight.getId()+"</h1>");
    out.println("<p>"+flight.getOrigin()+":"+flight.getDestination()+"</p>");
    out.println("<small>"+flight.getDate()+"</small>");
    for(Passanger passanger : services.getPassangersByFlight(flight.getId())) {
      out.println("<p>"+passanger.getName()+"</p>");
      out.println("<small>"+passanger.getPassportId()+"</small>");
    }
  }
%>

</body>
</html>