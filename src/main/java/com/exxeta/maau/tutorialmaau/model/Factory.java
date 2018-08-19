package com.exxeta.maau.tutorialmaau.model;

import com.exxeta.maau.tutorialmaau.model.enumeration.CarTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Factory {


    @Id
    @GeneratedValue
    private Long id;


    private CarTypes acceptedType;


    private String location;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
