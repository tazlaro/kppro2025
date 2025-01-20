package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Club;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ClubService {

    List<Club> getAllClubs();
    void saveClub(Club club);
    Club getClubById(Long id);
    void deleteClubById(Long id);
}
