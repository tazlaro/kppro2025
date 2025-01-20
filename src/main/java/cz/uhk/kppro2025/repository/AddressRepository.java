package cz.uhk.kppro2025.repository;

import cz.uhk.kppro2025.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
