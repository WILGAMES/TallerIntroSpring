package org.example.parcial_practica2.model;

public class Flight  {

    private String id;
    private String origin;
    private String destination;
    private String date;

    public Flight() {

    }

    public Flight(String id, String origin, String destination, String date) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }
}
