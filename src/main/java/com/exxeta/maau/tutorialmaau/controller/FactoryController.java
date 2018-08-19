package com.exxeta.maau.tutorialmaau.controller;


import com.exxeta.maau.tutorialmaau.model.Car;
import com.exxeta.maau.tutorialmaau.model.DailyProduction;
import com.exxeta.maau.tutorialmaau.model.Factory;
import com.exxeta.maau.tutorialmaau.model.request.AddFactoryRequest;
import com.exxeta.maau.tutorialmaau.repository.CarRepository;
import com.exxeta.maau.tutorialmaau.repository.DailyProductionRepository;
import com.exxeta.maau.tutorialmaau.repository.FactoryRepository;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    private FactoryRepository factoryRepository;
    private DailyProductionRepository dailyProductionRepo;
    private CarRepository carRepository;

    public FactoryController(FactoryRepository factoryRepository, CarRepository carRepository,
                             DailyProductionRepository dailyProductionRepository ){

        this.factoryRepository = factoryRepository;
        this.carRepository = carRepository;
        this.dailyProductionRepo = dailyProductionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Factory> getAllFactories(){
        return factoryRepository.findAll();
    }



    @RequestMapping(value = "/create",  method = RequestMethod.POST)
    public void addFactory(@RequestBody AddFactoryRequest addFactoryRequest){
        Factory factory = new Factory();
        factory.setAcceptedType(addFactoryRequest.getAcceptedType());
        factory.setLocation(addFactoryRequest.getLocation());

        factoryRepository.save(factory);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteFactory(@PathVariable("id") Long factoryId){

        factoryRepository.deleteById(factoryId);
    }




    @RequestMapping(value = "/{factoryId}/getCars", method = RequestMethod.GET)
    public List<Car> getCarsByFactoryId(@PathVariable (value = "factoryId") Long factoryId){

        List<Car> cars = new ArrayList<>();
        List<DailyProduction> dailyProductions = dailyProductionRepo.findByFactoryId(factoryId);
        for (DailyProduction dailyProduction : dailyProductions){
            List<Car> newCars;
            newCars = carRepository.findByDailyProductionId(dailyProduction.getId());
            for (Car car : newCars){
                cars.add(car);
            }
        }
        return cars;
    }



}
