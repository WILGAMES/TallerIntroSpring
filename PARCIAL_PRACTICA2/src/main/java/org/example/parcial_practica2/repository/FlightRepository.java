package org.example.parcial_practica2.repository;

import java.util.ArrayList;

import org.example.parcial_practica2.model.Flight;
import org.example.parcial_practica2.model.Passanger;
import org.springframework.stereotype.Repository;

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

    public void updatePassanger(Passanger passanger){
        for (Passanger p : passangers) {
            if(p.getId().equals(passanger.getId())){
                p.setName(passanger.getName());
                p.setPassportId(passanger.getPassportId());
                p.setFlightId(passanger.getFlightId());
            }
        }
    }
}
