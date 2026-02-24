package org.example.parcial_practica2.services;

import java.util.ArrayList;
import java.util.List;

import org.example.parcial_practica2.model.Flight;
import org.example.parcial_practica2.model.Passanger;
import org.example.parcial_practica2.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {

    @Autowired
    private FlightRepository flightRepository;

    public void addFlight(Flight flight){
        //Unicidad
        Flight foundFlight = flightRepository.getFlights().stream().filter(f -> f.getId().equals(flight.getId())).findFirst().orElse(null);

        if(foundFlight == null){
            flightRepository.addFlight(flight);
        }else {
            System.out.println("El vuelo ha sido agregado previamente");
        }

    }

    public ArrayList<Flight> getFlights(){
        return flightRepository.getFlights();
    }

    public List<Passanger> getPassangersByFlight(String flightId){
        return flightRepository.getPassangers().stream().filter(p -> p.getFlightId().equals(flightId)).toList();
    }

    public void addPassanger(Passanger passanger){
        //Regla particular
        Flight flight = flightRepository.getFlights().stream().filter(f -> f.getId().equals(passanger.getFlightId())).findFirst().orElse(null);
        Passanger foundPassanger = flightRepository.getPassangers().stream().filter(p -> p.getPassportId().equals(passanger.getPassportId())&&p.getFlightId().equals(passanger.getFlightId())).findFirst().orElse(null);

        if(flight != null){
            if (foundPassanger == null){
                flightRepository.addPassanger(passanger);
            }else {
                System.out.println("El pasajero ya existe dentro del mismo vuelo");
            }

        }else {
            System.out.println("El vuelo no existe");
        }
    }

    public void removeFlight(String flightId){

        Flight foundFlight = flightRepository.getFlights().stream().filter(f -> f.getId().equals(flightId)).findFirst().orElse(null);
        if(foundFlight == null){
            System.out.println("El vuelo no existe");
        }else{
            flightRepository.removeFlight(flightId);
        }
        
    }

    public void updatePassanger(Passanger passanger){
        Passanger foundPassanger = flightRepository.getPassangers().stream().filter(p -> p.getId().equals(passanger.getId())).findFirst().orElse(null);
        if(foundPassanger == null){
            System.out.println("El pasajero no existe");
        }else{
            flightRepository.updatePassanger(passanger);
        }
    }
    
}
