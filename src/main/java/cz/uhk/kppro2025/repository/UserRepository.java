package cz.uhk.kppro2025.repository;

import cz.uhk.kppro2025.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
