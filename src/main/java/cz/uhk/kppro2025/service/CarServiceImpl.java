package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Car;
import cz.uhk.kppro2025.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(Long index) {
        return carRepository.findById(index).orElse(null);
    }

    @Override
    public boolean addCar(Car car) {
        carRepository.save(car);
        return true;
    }

    @Override
    public boolean updateCar(Car car) {
        Optional<Car> carDB = carRepository.findById(car.getId());
        if(carDB.isPresent()){
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public Car deleteCar(Long id) {
        Optional<Car> carDB = carRepository.findById(id);
        if(carDB.isPresent()){
            Car car = carDB.get();
            carRepository.delete(carDB.get());
            return car;
        }
        return null;
    }
}
