package org.example.parcial_practica2.repository;

import org.example.parcial_practica2.model.Flight;
import org.example.parcial_practica2.model.Passanger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FlightRepository {

    ArrayList<Flight> flights = new ArrayList<>();
    ArrayList<Passanger> passangers = new ArrayList<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addPassanger(Passanger passanger) {
        passangers.add(passanger);
    }

    public ArrayList<Passanger> getPassangers() {
        return passangers;
    }

    public void removeFlight(String flightId){
        flights.removeIf(f -> f.getId().equals(flightId));
        passangers.removeIf(p -> p.getFlightId().equals(flightId));
    }
}
