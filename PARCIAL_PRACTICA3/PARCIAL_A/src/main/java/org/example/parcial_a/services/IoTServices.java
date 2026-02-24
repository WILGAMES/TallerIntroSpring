package org.example.parcial_a.services;


import org.example.parcial_a.model.Device;
import org.example.parcial_a.model.Measurement;
import org.example.parcial_a.repository.DeviceRepository;
import org.example.parcial_a.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IoTServices {

    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public void addDevice(Device device) {
        if (device.getName() == null || device.getName().isEmpty()){
            return;
        }

        if (device.getSerialName() == null || device.getSerialName().length() < 5) {
            return;
        }

        Device existing = deviceRepository.getDevices().stream()
            .filter(d -> d.getId().equals(device.getId()))
            .findFirst()
            .orElse(null);
        
        if (existing != null) {
            return;
        }

        deviceRepository.addDevice(device);
    }

    public List<Device> getDevices() {
        return deviceRepository.getDevices();
    }

    public ArrayList<Measurement> getMeasurements() {
        return measurementRepository.getMeasurements();
    }

    public void updateState(Integer id, String state){
        Device foundDevice = deviceRepository.getDevices().stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);

        if (foundDevice == null) {
                System.out.println("No existe");
                return;
        }
        foundDevice.setStatus(state);
    }

}
