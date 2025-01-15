package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Driver;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DriverService {
    List<Driver> getAllDrivers();
    Driver getDriver(Long id);
    boolean addDriver(Driver driver);
    boolean updateDriver(Driver driver);
    Driver deleteDriver(Long id);
}
