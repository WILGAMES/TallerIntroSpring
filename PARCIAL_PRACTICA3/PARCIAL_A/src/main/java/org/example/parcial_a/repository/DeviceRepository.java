package org.example.parcial_a.repository;


import org.example.parcial_a.model.Device;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class DeviceRepository {

    static List<Device> devices =  new ArrayList<>();

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

}
