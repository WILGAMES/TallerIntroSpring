package org.example.parcial_a.model;

public class Measurement {

    private int id;
    private String timestamp;
    private String value;
    private String unit;
    private int deviceId;

    public Measurement() {}

    public Measurement(int id, int deviceId, String unit, String value, String timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.unit = unit;
        this.value = value;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
