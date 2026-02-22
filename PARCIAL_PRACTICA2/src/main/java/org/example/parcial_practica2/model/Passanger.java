package org.example.parcial_practica2.model;

public class Passanger {

    private String id;
    private String name;
    private String passportId;
    private String flightId;

    public Passanger() {}

    public Passanger(String id, String name, String passportId, String flightId) {
        this.id = id;
        this.flightId = flightId;
        this.passportId = passportId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
