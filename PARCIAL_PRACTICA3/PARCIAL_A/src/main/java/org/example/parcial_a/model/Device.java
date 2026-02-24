package org.example.parcial_a.model;

import java.util.ArrayList;
import java.util.List;

public class Device {

    private Integer id;
    private String name;
    private String serialName;
    private String location;
    private String type;
    private String status;

    public Device(){

    }

    public Device(Integer id, String status, String type, String location, String serialName, String name) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.location = location;
        this.serialName = serialName;
        this.name = name;
    }

    private List<Measurement> measurement = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }
}
