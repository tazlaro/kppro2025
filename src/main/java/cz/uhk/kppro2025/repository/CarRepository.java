package cz.uhk.kppro2025.repository;

import cz.uhk.kppro2025.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
