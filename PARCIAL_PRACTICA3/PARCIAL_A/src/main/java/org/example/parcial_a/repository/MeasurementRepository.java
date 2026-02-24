package org.example.parcial_a.repository;

import org.example.parcial_a.model.Measurement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MeasurementRepository {

    static ArrayList<Measurement> measurements = new ArrayList<>();

    public ArrayList<Measurement> getMeasurements() {
        return measurements;
    }


}
