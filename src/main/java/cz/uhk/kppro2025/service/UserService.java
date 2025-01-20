package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);

    // NEW: -------------------------------

    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
}
