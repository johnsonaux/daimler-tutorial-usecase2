package com.exxeta.maau.tutorialmaau.controller;


import com.exxeta.maau.tutorialmaau.exception.ResourceNotFoundException;
import com.exxeta.maau.tutorialmaau.model.Car;
import com.exxeta.maau.tutorialmaau.model.request.AddCarRequest;
import com.exxeta.maau.tutorialmaau.repository.CarRepository;
import com.exxeta.maau.tutorialmaau.repository.DailyProductionRepository;
import com.exxeta.maau.tutorialmaau.repository.FactoryRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private FactoryRepository factoryRepository;
    private DailyProductionRepository dailyProductionRepo;
    private CarRepository carRepository;


    public CarController (FactoryRepository factoryRepository, CarRepository carRepository, DailyProductionRepository dailyProductionRepo){
        this.factoryRepository = factoryRepository;
        this.carRepository = carRepository;
        this.dailyProductionRepo = dailyProductionRepo;
    }

    //CREATE new car and set dailyProd
    @RequestMapping(value = "/{dailyProdId}/create", method = RequestMethod.POST)
    public Car addCarToDailyProd(@PathVariable (value = "dailyProdId") Long dailyProdId,
                                @RequestBody Car addCarRequest){

        return dailyProductionRepo.findById(dailyProdId)
                .map(dailyProduction -> {
                    if (!dailyProduction.getFactory().getAcceptedType().equals(addCarRequest.getType())){
                        throw new ResourceNotFoundException("Car type not accepted at this Factory - seems like" +
                                "being the wrong Daily Production!");
                    } else {
                        addCarRequest.setDailyProduction(dailyProduction);
                    }
                    return carRepository.save(addCarRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("DailyProduction not found with id " + dailyProdId));
    }

    //RETRIEVE all cars
    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //UPDATE car vehicleModel
    @RequestMapping(value = "/updateModel/{carId}", method = RequestMethod.PUT)
    public void updateCarModel(@PathVariable (value= "carId") Long carId, @RequestBody AddCarRequest addCarRequest){
        carRepository.findById(carId).map(car -> {
            car.setVehicleModel(addCarRequest.getVehicleModel());
            return carRepository.save(car);
        });
    }


    //DELETE Car via carId
    @RequestMapping(value = "/delete/{carId}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("carId") Long carId){
        carRepository.deleteById(carId);
    }

   /*-----------
      CREATE CAR WITHOUT DAILY_PROD_ID AND SET DAILY_PROD_ID VIA UPDATE METHOD
      ----------------------- */

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addCar(@RequestBody AddCarRequest addCarRequest) {
        Car car = new Car();
        car.setType(addCarRequest.getType());
        car.setVehicleClass(addCarRequest.getVehicleClass());
        car.setVehicleModel(addCarRequest.getVehicleModel());
        carRepository.save(car);
    }

    //UPDATE
    @RequestMapping(value = "/{carId}/setDailyProd/{dailyProdId}", method = RequestMethod.PUT)
    public void updateCar(@PathVariable (value = "carId") Long carId,
                          @PathVariable (value = "dailyProdId") Long dailyProdId){
        List<Car> allCars =  carRepository.findAll();
        Car selectedCar = new Car();
        for (Car car: allCars){
            if (car.getId().equals(carId)){
                selectedCar = car;
            }
        }
        final Car carToStore = selectedCar;
        if (!selectedCar.equals(null)){
            dailyProductionRepo.findById(dailyProdId)
                    .map(dailyProduction -> {
                        if (dailyProduction.getFactory() == null){
                            throw new ResourceNotFoundException("Please first add DailyProduction to a Factory");
                        } else {
                            if (!dailyProduction.getFactory().getAcceptedType().equals(carToStore.getType())){
                                throw new ResourceNotFoundException("Car Type not accepted in DailyProd´s Factory");
                            } else {
                                carToStore.setDailyProduction(dailyProduction);
                            }
                        }
                        return carRepository.save(carToStore);
                    });
        }

    }


}
