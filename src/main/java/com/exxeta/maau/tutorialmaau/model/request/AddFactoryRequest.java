package com.exxeta.maau.tutorialmaau.model.request;

import com.exxeta.maau.tutorialmaau.model.enumeration.CarTypes;

public class AddFactoryRequest {


    private CarTypes acceptedType;
    private String location;


    public CarTypes getAcceptedType() {
        return acceptedType;
    }

    public void setAcceptedType(CarTypes acceptedType) {
        this.acceptedType = acceptedType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
