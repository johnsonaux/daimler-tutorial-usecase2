package com.exxeta.maau.tutorialmaau.model;

import com.exxeta.maau.tutorialmaau.model.enumeration.CarTypes;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private CarTypes type;

    private String vehicleClass;

    private String vehicleModel;

    //second version add @ManyToOne(fetch = FetchType.LAZY) and @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "dailyProduction_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DailyProduction dailyProduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public DailyProduction getDailyProduction() {
        return dailyProduction;
    }

    public void setDailyProduction(DailyProduction dailyProduction) {
        this.dailyProduction = dailyProduction;
    }
}
