package com.exxeta.maau.tutorialmaau.model.request;

import com.exxeta.maau.tutorialmaau.model.enumeration.CarTypes;

public class AddCarRequest {

    private CarTypes type;
    private String vehicleClass;
    private String vehicleModel;

    public CarTypes getType() {
        return type;
    }

    public void setType(CarTypes type) {
        this.type = type;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
