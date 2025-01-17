package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(User user);
}
