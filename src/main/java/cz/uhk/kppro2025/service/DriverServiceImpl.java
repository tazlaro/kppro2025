package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Driver;
import cz.uhk.kppro2025.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService{

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriver(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addDriver(Driver driver) {
        driverRepository.save(driver);
        return true;
    }

    @Override
    public boolean updateDriver(Driver driver) {
        Optional<Driver> driverDB = driverRepository.findById(driver.getId());
        if(driverDB.isPresent()){
            driverRepository.save(driver);
            return true;
        }
        return false;
    }

    @Override
    public Driver deleteDriver(Long id) {
        Optional<Driver> driverDB = driverRepository.findById(id);
        if(driverDB.isPresent()){
            Driver driver = driverDB.get();
            driverRepository.delete(driverDB.get());
            return driver;
        }
        return null;
    }
}
