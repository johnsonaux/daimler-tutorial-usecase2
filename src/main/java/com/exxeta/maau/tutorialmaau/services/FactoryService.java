package com.exxeta.maau.tutorialmaau.services;

import com.exxeta.maau.tutorialmaau.model.Car;
import com.exxeta.maau.tutorialmaau.model.Factory;

import java.util.List;

public interface FactoryService {

    List<Factory> getAllFactories();

    void addFactory(Factory factory);

    List<Car> getCarsInFactory(Long factoryId);

    void deleteFactory(Long factoryId);

}
