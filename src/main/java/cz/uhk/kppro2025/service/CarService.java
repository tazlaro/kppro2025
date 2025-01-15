package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    List<Car> getAllCars();
    Car getCar(int index);
    boolean addCar(Car car);
    boolean updateCar(Car car);
    Car deleteCar(int index);
}
