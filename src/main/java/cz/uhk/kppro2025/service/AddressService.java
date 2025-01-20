package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Address;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AddressService {

    List<Address> getAllAddresses();
    void saveAddress(Address address);
    Address getAddressById(Long id);
    void deleteAddressById(Long id);
}
